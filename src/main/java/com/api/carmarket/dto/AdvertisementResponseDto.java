package com.api.carmarket.dto;

import com.api.carmarket.model.Car;
import com.api.carmarket.model.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdvertisementResponseDto {
    private String advertisementId;
    private String userId;
    private Date postDate;
    private Date expireDate;
    private boolean isSold;
    private int views;
    private Location location;
    private boolean isAuctionPossible;
    private boolean isExchangePossible;
    private boolean isExchangeForRealtyPossible;
    private int price;
    private String currency;
    private Car car;
}
