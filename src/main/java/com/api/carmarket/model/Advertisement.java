package com.api.carmarket.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class Advertisement {
    @Id
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
    private boolean isModerated;
}
