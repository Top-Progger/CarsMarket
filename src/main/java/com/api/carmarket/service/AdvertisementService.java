package com.api.carmarket.service;

import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.dto.AdvertisementResponseDto;
import com.api.carmarket.model.Advertisement;

import java.util.List;

public interface AdvertisementService {
    Advertisement create(AdvertisementCreateDto advertisementCreateData);

    long delete(String userId, String advertisementId);

    AdvertisementResponseDto get(String advertisementId);

    List<AdvertisementResponseDto> getUserAdvertisements(String userId);

    void increaseAdvertisementViews(String advertisementId);
}
