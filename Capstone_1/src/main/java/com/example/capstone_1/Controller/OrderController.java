package com.example.capstone_1.Controller;

import com.example.capstone_1.Model.Order;
import com.example.capstone_1.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getOrders(){
        return ResponseEntity.status(200).body(orderService.getOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@Valid @RequestBody Order order , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(orderService.addOrder(order).equals("true")){
            return ResponseEntity.status(200).body("Order added successfully");
        }
        return ResponseEntity.status(400).body(orderService.addOrder(order));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable int id, @Valid @RequestBody Order order, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(orderService.updateOrder(id, order).equals("true")){
            return ResponseEntity.status(200).body("Order updated successfully");
        }
        return ResponseEntity.status(400).body(orderService.updateOrder(id, order));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable int id){
        if(orderService.deleteOrder(id)){
            return ResponseEntity.status(200).body("Order deleted successfully");
        }
        return ResponseEntity.status(400).body("Order not found");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity updateOrderStatus(@PathVariable int id){
        if (orderService.updateStatus(id).equals("true")){
            return ResponseEntity.status(200).body(orderService.updateStatus(id));
        }
        return ResponseEntity.status(400).body(orderService.updateStatus(id));
    }














}
