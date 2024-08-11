package com.example.capstone_1.Service;

import com.example.capstone_1.Model.MerchantStock;
import com.example.capstone_1.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {



    final MerchantService merchantService;
    final ProductService productService;
    final MerchantStockRepository merchantStockRepository;




    public ArrayList<MerchantStock> getMerchantStocks() {
        return new ArrayList<MerchantStock>(merchantStockRepository.findAll());
    }

    public String addMerchantStock(MerchantStock merchantStock) {
        if (productService.existsById(merchantStock.getProductId())){
            if(merchantService.existsById(merchantStock.getMerchantId())){
                merchantStockRepository.save(merchantStock);
                return "true";
            }
            return "Merchant does not exist";
        }
        return "Product does not exist";
    }

    public String updateMerchantStock(int id,MerchantStock merchantStock) {
        MerchantStock m = merchantStockRepository.getById(id);
        if (m != null){
            if (productService.existsById(merchantStock.getProductId())){
                if(merchantService.existsById(merchantStock.getMerchantId())){
                    m.setMerchantId(merchantStock.getMerchantId());
                    m.setProductId(merchantStock.getProductId());
                    m.setStock(merchantStock.getStock());
                    merchantStockRepository.save(m);
                    return "true";
                }
                return "Merchant does not exist";
            }
            return "Product does not exist";
        }

        return "Merchant Stock does not exist";
    }

    public boolean deleteMerchantStock(int id) {
        if (merchantService.existsById(id)){
            merchantStockRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String addStock(int merchantStockId , int stock){
        MerchantStock m = merchantStockRepository.getById(merchantStockId);
        if (m != null){
            m.setStock(m.getStock()+stock);
            merchantStockRepository.save(m);
            return "true";
        }
        return "Merchant Stock does not exist";
    }

    public boolean isThereStock(int productId , int merchantId){
        if (productService.existsById(productId)&&merchantService.existsById(merchantId)){
            for (MerchantStock merchantStock : getMerchantStocks()) {
                if (merchantStock.getProductId()==productId&&merchantStock.getMerchantId()==merchantId&&merchantStock.getStock()>0){
                    merchantStock.setStock(merchantStock.getStock()-1);
                    return true;
                }
            }
        }
        return false;
    }


    }




















