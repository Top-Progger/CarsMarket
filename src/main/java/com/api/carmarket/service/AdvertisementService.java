package com.api.carmarket.service;

import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.model.Advertisement;

public interface AdvertisementService {
    Advertisement create(AdvertisementCreateDto advertisementCreateData);

    long delete(String userId, String advertisementId);
}
