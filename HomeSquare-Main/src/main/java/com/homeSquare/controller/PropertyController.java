package com.homeSquare.controller;

import com.homeSquare.payload.PropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.homeSquare.service.PropertyService;

import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> addProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO savedProperty = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    // You can add more CRUD endpoints or additional functionality as needed

    @PatchMapping("/{propertyId}/approve")
    public ResponseEntity<PropertyDTO> approveProperty(@PathVariable Long propertyId) {
        PropertyDTO approvedProperty = propertyService.approveProperty(propertyId);
        return new ResponseEntity<>(approvedProperty, HttpStatus.OK);
    }

    @GetMapping("/{propertyId}/read")
    public ResponseEntity<PropertyDTO> readProperty(@PathVariable Long propertyId) {
        Optional<PropertyDTO> propertyDTOOptional = propertyService.getPropertyById(propertyId);

        return propertyDTOOptional
                .map(propertyDTO -> new ResponseEntity<>(propertyDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

