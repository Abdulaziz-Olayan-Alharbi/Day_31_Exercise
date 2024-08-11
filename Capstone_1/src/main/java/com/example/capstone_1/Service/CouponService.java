package com.example.capstone_1.Service;

import com.example.capstone_1.Model.Coupon;
import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CouponService {



    final CouponRepository couponRepository;
    final MerchantService merchantService;
    final ProductService productService;


    public ArrayList<Coupon> getCoupons() {
        return new ArrayList<Coupon>(couponRepository.findAll());
    }

    public String addCoupon(Coupon coupon) {
        if (productService.existsById(coupon.getProductId())){
            if (merchantService.existsById(coupon.getMerchantId())){
                couponRepository.save(coupon);
                return "true";
            }
            return "Merchant does not exist";
        }
        return "Product does not exist";
    }

    public String updateCoupons(int id,Coupon coupon) {
        Coupon c = couponRepository.getById(id);
        if (c != null){
            if (productService.existsById(coupon.getProductId())){
                if (merchantService.existsById(coupon.getMerchantId())){
                    c.setPercent(coupon.getPercent());
                    c.setMerchantId(c.getMerchantId());
                    c.setProductId(coupon.getProductId());
                    couponRepository.save(c);
                    return "true";
                }
                return "Merchant does not exist";
            }
            return "Product does not exist";
        }
        return "Coupon does not exist";
    }

    public boolean deleteCoupon(int id) {
        if (couponRepository.existsById(id)){
            couponRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ArrayList<Coupon> getCouponsProduct(int id) {
        ArrayList<Coupon> couponsProduct = new ArrayList<>();
        for (int i = 0; i < getCoupons().size(); i++) {
            if (getCoupons().get(i).getProductId()==id) {
                couponsProduct.add(getCoupons().get(i));
            }
        }
        return couponsProduct;
    }
}
