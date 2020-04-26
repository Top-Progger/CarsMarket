package com.api.carmarket.dto;

import com.api.carmarket.model.Car;
import com.api.carmarket.model.Location;
import com.api.carmarket.model.Options;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisementCreateDto {
    private String userId;
    private Location location;
    private boolean isAuctionPossible;
    private boolean isExchangePossible;
    private boolean isExchangeForRealtyPossible;
    private int price;
    private Car car;
    private Options options;
}
