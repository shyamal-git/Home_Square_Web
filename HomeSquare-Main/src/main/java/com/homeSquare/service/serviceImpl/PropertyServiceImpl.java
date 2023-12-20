package com.homeSquare.service.serviceImpl;

import com.homeSquare.entity.Property;
import com.homeSquare.payload.PropertyDTO;
import com.homeSquare.repository.PropertyRepository;
import com.homeSquare.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PropertyDTO> getPropertyById(Long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        return property.map(this::convertToDTO);
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        Property property = convertToEntity(propertyDTO);
        property.setStatus("pending");
        property = propertyRepository.save(property);
        return convertToDTO(property);
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }


    @Override
    public PropertyDTO approveProperty(Long propertyId) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);

        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            property.setStatus("approved");
            property = propertyRepository.save(property);
            return convertToDTO(property);
        } else {
            // Handle property not found
            // You can throw an exception or return null/empty, depending on your design
            return null;
        }
    }

    private PropertyDTO convertToDTO(Property property) {
        return new PropertyDTO(
                property.getPropertyId(),
                property.getApartmentType(),
                property.getBhkType(),
                property.getFloor(),
                property.getTotalFloor(),
                property.getPropertyAge(),
                property.getFacing(),
                property.getBuildUpArea(),
                property.getStatus()
        );
    }

    private Property convertToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setApartmentType(propertyDTO.getApartmentType());
        property.setBhkType(propertyDTO.getBhkType());
        property.setFloor(propertyDTO.getFloor());
        property.setTotalFloor(propertyDTO.getTotalFloor());
        property.setPropertyAge(propertyDTO.getPropertyAge());
        property.setFacing(propertyDTO.getFacing());
        property.setBuildUpArea(propertyDTO.getBuildUpArea());
        property.setStatus(propertyDTO.getStatus());
        return property;
    }
}
