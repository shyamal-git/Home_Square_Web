package com.homeSquare.service;

import com.homeSquare.payload.PropertyDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    List<PropertyDTO> getAllProperties();

    Optional<PropertyDTO> getPropertyById(Long propertyId);

    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    void deleteProperty(Long propertyId);

    PropertyDTO approveProperty(Long propertyId);
}
