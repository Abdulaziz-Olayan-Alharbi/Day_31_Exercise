package com.example.capstone_1.Service;

import com.example.capstone_1.Model.Merchant;
import com.example.capstone_1.Model.Order;
import com.example.capstone_1.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {


    final OrderService orderService;

    ArrayList<Order> orders = orderService.getOrders();

    final MerchantRepository merchantRepository;

    public ArrayList<Merchant> getMerchants() {
        return new ArrayList<Merchant>(merchantRepository.findAll());
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(int id,Merchant merchant) {
        Merchant m = merchantRepository.getById(id);
        if (merchantRepository.existsById(id)) {
            m.setName(merchant.getName());
            merchantRepository.save(m);
            return true;
        }
        return false;
    }

    public boolean deleteMerchant(int id) {
        if (merchantRepository.existsById(id)) {
            merchantRepository.deleteById(id);
            return true;
        }
        return false;
    }



    public boolean existsById(int id) {
        return merchantRepository.existsById(id);
    }



















}
