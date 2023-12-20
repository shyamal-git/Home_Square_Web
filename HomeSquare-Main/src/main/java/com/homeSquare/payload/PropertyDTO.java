package com.homeSquare.payload;

import lombok.Data;

@Data

public class PropertyDTO {

    private Long propertyId;
    private String apartmentType;
    private String bhkType;
    private int floor;
    private int totalFloor;
    private int propertyAge;
    private String facing;
    private double buildUpArea;
    private String status;

    // Constructors, getters, and setters

    // You can generate constructors, getters, and setters using your IDE or manually.

    // Example constructor
    public PropertyDTO() {
    }

    // Example constructor with parameters
    public PropertyDTO(Long propertyId, String apartmentType, String bhkType, int floor, int totalFloor, int propertyAge, String facing, double buildUpArea, String status) {
        this.propertyId = propertyId;
        this.apartmentType = apartmentType;
        this.bhkType = bhkType;
        this.floor = floor;
        this.totalFloor = totalFloor;
        this.propertyAge = propertyAge;
        this.facing = facing;
        this.buildUpArea = buildUpArea;
        this.status = status;
    }

    // Example getters and setters
    // ...

    // Example toString method
    @Override
    public String toString() {
        return "PropertyDTO{" +
                "propertyId=" + propertyId +
                ", apartmentType='" + apartmentType + '\'' +
                ", bhkType='" + bhkType + '\'' +
                ", floor=" + floor +
                ", totalFloor=" + totalFloor +
                ", propertyAge=" + propertyAge +
                ", facing='" + facing + '\'' +
                ", buildUpArea=" + buildUpArea +
                ", status='" + status + '\'' +
                '}';
    }
}
