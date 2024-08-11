package com.example.capstone_1.Controller;

import com.example.capstone_1.Api.ApiResponse;
import com.example.capstone_1.Model.User;
import com.example.capstone_1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id,@Valid @RequestBody User user, Errors errors ) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.updateUser(id,user)) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if(userService.deleteUser(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        if (userService.buyProduct(userId, productId, merchantId).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("User bought successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(userService.buyProduct(userId, productId, merchantId)));
    }

    @PutMapping("/buy/{userId}/{productId}/{merchantId}/{couponId}")
    public ResponseEntity buyProductWithCoupon(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId,@PathVariable int couponId) {
        if (userService.buyProductWithCoupon(userId, productId, merchantId, couponId).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("User bought successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(userService.buyProductWithCoupon(userId, productId, merchantId, couponId)));
    }

    @PutMapping("/cancel/{userId}/{orderId}")
    public ResponseEntity cancelOrder (@PathVariable int userId ,@PathVariable int orderId){
        if (userService.cancelOrder(userId, orderId).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Order cancelled successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(userService.cancelOrder(userId, orderId)));
    }


















































}
