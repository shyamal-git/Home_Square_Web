package com.homeSquare.repository;

import com.homeSquare.entity.LegalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalProductRepository extends JpaRepository<LegalProduct, Long> {
}