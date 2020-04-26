package com.api.carmarket.service.impl;

import com.api.carmarket.dao.AdvertisementDao;
import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.dto.AdvertisementResponseDto;
import com.api.carmarket.model.Advertisement;
import com.api.carmarket.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementDao advertisementDao;

    @Override
    public Advertisement create(AdvertisementCreateDto advertisementCreateData) {
        if (advertisementCreateData != null) {
            return advertisementDao.create(advertisementCreateData);
        } else {
            return null;
        }
    }

    @Override
    public long delete(String userId, String advertisementId) {
        long result = -1;
        if (userId != null && advertisementId != null) {
            result = advertisementDao.delete(userId, advertisementId);
        }
        return result;
    }

    @Override
    public AdvertisementResponseDto get(String advertisementId) {
        return advertisementDao.get(advertisementId);
    }

    @Override
    public List<AdvertisementResponseDto> getUserAdvertisements(String userId) {
        return advertisementDao.getUserAdvertisements(userId);
    }

    @Override
    public void increaseAdvertisementViews(String advertisementId) {
        advertisementDao.incrementAdvertisementViews(advertisementId);
    }
}
