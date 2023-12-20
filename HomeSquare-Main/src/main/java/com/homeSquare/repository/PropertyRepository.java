package com.homeSquare.repository;

import com.homeSquare.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // You can define custom queries or methods here if needed.
    // Spring Data JPA provides methods like save(), findById(), findAll(), deleteById(), etc., out of the box.

    // Example custom query method
    // Optional<Property> findByApartmentTypeAndBhkType(String apartmentType, String bhkType);
}

