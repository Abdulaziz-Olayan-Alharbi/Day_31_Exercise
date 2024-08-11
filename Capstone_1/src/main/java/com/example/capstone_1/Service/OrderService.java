package com.example.capstone_1.Service;

import com.example.capstone_1.Model.Order;
import com.example.capstone_1.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderService {



    final OrderRepository orderRepository;

    final ProductService productService;





    public ArrayList<Order> getOrders() {
        return new ArrayList<Order>(orderRepository.findAll());
    }

    public String addOrder(Order order) {
        if (productService.existsById(order.getProductId())) {
            orderRepository.save(order);
            return "true";

        }
        return "product does not exist";

    }

    public String updateOrder(int id,Order order) {
        Order o = orderRepository.getById(id);
        if (o != null){
            if (productService.existsById(order.getProductId())) {
                o.setUserId(order.getUserId());
                o.setMerchantId(order.getMerchantId());
                o.setProductId(order.getProductId());
                o.setPrice(order.getPrice());
                o.setStatus(order.getStatus());
                orderRepository.save(o);
                return "true";
            }
            return "Product does not exist";
        }
        return "Order does not exist";
    }

    public boolean deleteOrder(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String updateStatus(int id) {
        Order o = orderRepository.getById(id);
        if (o != null) {
            if (o.getStatus().equals("Received")){
                o.setStatus("Shipping");
                orderRepository.save(o);
                return "true";
            }
            if (o.getStatus().equals("Shipping")){
                o.setStatus("Shipped");
                orderRepository.save(o);
                return "true";
            }
            return "it is shipped , can nou update status";
        }
        return "Order does not exist";
    }
}
