package com.api.carmarket.dao;

import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.model.Advertisement;

public interface AdvertisementDao {
    Advertisement create(AdvertisementCreateDto advertisementCreateData);

    long delete(String userId, String advertisementId);
}
