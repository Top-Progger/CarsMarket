package com.api.carmarket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Options {
    private boolean isDamaged;
    private boolean isCustomCleared;
    private String VIN;
    private String gearBoxType;
    private String drive;
    private String fuelType;
    private int fuelConsumptionInCity;
    private int fuelConsumptionInRoute;
    private int combineModeFuelConsumption;
    private float engineVolume;
    private int horsePowers;
    private float kiloWatts;
    private String color;
}
