package com.example.capstone_1.Controller;


import com.example.capstone_1.Api.ApiResponse;
import com.example.capstone_1.Model.Coupon;
import com.example.capstone_1.Service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {

    final CouponService couponService;

    @GetMapping("/get")
    public ResponseEntity getCoupon() {
        return ResponseEntity.status(200).body(couponService.getCoupons());
    }

    @PostMapping("/add")
    public ResponseEntity addCoupon(@Valid @RequestBody Coupon coupon,Errors errors) {
        if (couponService.addCoupon(coupon).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Coupon added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(couponService.addCoupon(coupon)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCoupon(@PathVariable int id,@Valid @RequestBody Coupon coupon, Errors errors ) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (couponService.updateCoupons(id, coupon).equals("true")){
            return ResponseEntity.status(200).body(new ApiResponse("Coupon updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(couponService.updateCoupons(id, coupon)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoupon(@PathVariable int id) {
        if (couponService.deleteCoupon(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Coupon deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Coupon ID does not exist"));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getCouponByProductId(@PathVariable int productId) {
        return ResponseEntity.status(200).body(couponService.getCouponsProduct(productId));
    }











}













