package com.example.capstone_1.Controller;

import com.example.capstone_1.Api.ApiResponse;
import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (merchantStockService.addMerchantStock(merchantStock).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
        }
        return ResponseEntity.status(400).body(merchantStockService.addMerchantStock(merchantStock));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id,@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (merchantStockService.updateMerchantStock(id,merchantStock).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated Successfully"));
        }
        return ResponseEntity.status(400).body(merchantStockService.updateMerchantStock(id,merchantStock));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        if (merchantStockService.deleteMerchantStock(id)) {
            return ResponseEntity.status(200).body("Merchant Stock Deleted Successfully");
        }
        return ResponseEntity.status(400).body("Merchant Stock not found");
    }

    @PutMapping("/additional/{merchantStockId}/{stock}")
    public ResponseEntity addStock (@PathVariable int merchantStockId , @PathVariable int stock){
        if (merchantStockService.addStock(merchantStockId,stock).equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Stock Added Successfully"));
        }
        return ResponseEntity.status(400).body(merchantStockService.addStock(merchantStockId,stock));
    }






























}
