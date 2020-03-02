package com.api.carmarket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private String brand;
    private String model;
    private int year;
    private int mileage;
    private String category;
    private String bodyType;
    private String modification;
    private boolean isDamaged;
}
