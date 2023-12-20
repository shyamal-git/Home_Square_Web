package com.homeSquare.service.serviceImpl;

import com.homeSquare.entity.LegalProduct;
import com.homeSquare.repository.LegalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalProductService {

    @Autowired
    private LegalProductRepository legalProductRepository;

    public List<LegalProduct> getAllLegalProducts() {
        return legalProductRepository.findAll();
    }

    public LegalProduct getLegalProductById(Long id) {
        return legalProductRepository.findById(id).orElse(null);
    }
}