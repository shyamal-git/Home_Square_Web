package com.homeSquare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Data
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "apartment_type", nullable = false)
    private String apartmentType;

    @Column(name = "bhk_type", nullable = false)
    private String bhkType;

    @Column(name = "floor", nullable = false)
    private int floor;

    @Column(name = "total_floor", nullable = false)
    private int totalFloor;

    @Column(name = "property_age")
    private int propertyAge;

    @Column(name = "facing")
    private String facing;

    @Column(name = "build_up_area", nullable = false)
    private double buildUpArea;

    @Column(name = "status")
    private String status;

    // Constructors, getters, and setters

    // You can generate constructors, getters, and setters using your IDE or manually.

    // Example constructor
    public Property() {
    }

    // Example constructor with parameters
    public Property(String apartmentType, String bhkType, int floor, int totalFloor, int propertyAge, String facing, double buildUpArea, String status) {
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
        return "Property{" +
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
