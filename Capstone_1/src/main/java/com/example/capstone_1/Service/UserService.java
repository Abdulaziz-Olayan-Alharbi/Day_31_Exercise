package com.example.capstone_1.Service;


import com.example.capstone_1.Model.Coupon;
import com.example.capstone_1.Model.Order;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {



    final UserRepository userRepository;
    final MerchantStockService merchantStockService;
    final ProductService productService;
    final CouponService couponService;
    final OrderService orderService;

    public ArrayList<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(int id ,User user) {
        User u = userRepository.getById(id);
        if (u != null) {
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setBalance(user.getBalance());
            u.setEmail(user.getEmail());
            u.setRole(user.getRole());
            userRepository.save(u);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String buyProduct(int userId , int productId , int merchantId ) {
        User u = userRepository.getById(userId);
        if (u != null) {
            for (Product product : productService.getProducts()) {
                if (product.getId() == productId) {
                    if (u.getBalance() >= product.getPrice()) {
                        if (u.getRole().equals("Customer")) {
                            if (merchantStockService.isThereStock(productId, merchantId)) {
                                u.setBalance(u.getBalance() - product.getPrice());
                                userRepository.save(u);
                                orderService.addOrder(new Order(1, userId, productId, merchantId, "Received", product.getPrice()));
                                return "true";
                            }
                            return "Merchant Stock does not exist";
                        }
                        return "User must be a Customer";
                    }
                    return "User does not have enough balance";
                }
                return "Product does not exist";
            }
        }
        return "User not found";
    }


    public String buyProductWithCoupon(int userId , int productId , int merchantId , int couponId) {
        User u = userRepository.getById(userId);
        if (u != null) {
            for (Coupon coupon : couponService.getCoupons()) {
                if (coupon.getId() == couponId && coupon.getMerchantId() == merchantId && coupon.getProductId() == productId) {
                    for (Product product : productService.getProducts()) {
                        if (product.getId() == productId) {
                            if (u.getBalance() >= product.getPrice()-((product.getPrice()/100) * coupon.getPercent())) {
                                if (u.getRole().equals("Customer")){
                                    if (merchantStockService.isThereStock(productId, merchantId)) {
                                        u.setBalance(u.getBalance() - (product.getPrice() - ((product.getPrice() / 100) * coupon.getPercent())));
                                        userRepository.save(u);
                                        orderService.addOrder(new Order(1, userId, productId, merchantId, "Received",(product.getPrice() - ((product.getPrice() / 100) * coupon.getPercent()))));
                                        return "true";
                                    }
                                    return "Merchant does not exist";
                                }
                                return "User must be a Customer";
                            }
                            return "User does not have enough balance";
                        }
                        return "Product does not exist";
                    }
                }
                return "Coupon does not exist";
            }
        }
        return "User not found";
    }

    public String cancelOrder(int userId , int orderId){
        User u = userRepository.getById(userId);
        if (u != null) {
            if (u.getRole().equals("Admin")) {
                for (Order order : orderService.getOrders()) {
                    if (order.getId() == orderId) {
                        String status = order.getStatus();
                        if (status.equals("Received")||status.equals("Shipping")) {
                            order.setStatus("Cancelled");
                            orderService.updateOrder(orderId,order);
                            for (User user : getUsers()) {
                                if (user.getId() == order.getUserId()) {
                                    user.setBalance(user.getBalance()+order.getPrice());
                                    updateUser(user.getId(),user);
                                }
                            }
                        }
                        return "Order can not be cancelled";
                    }
                }
                return "Order does not exist";
            }
            return "User is not Admin";
        }
        return "User not found";
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }






















}
