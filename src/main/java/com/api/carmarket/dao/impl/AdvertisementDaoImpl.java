package com.api.carmarket.dao.impl;

import com.api.carmarket.dao.AdvertisementDao;
import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.dto.AdvertisementResponseDto;
import com.api.carmarket.model.Advertisement;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
@AllArgsConstructor
public class AdvertisementDaoImpl implements AdvertisementDao {
    private final String COLLECTION_NAME = "advertisements";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Advertisement create(AdvertisementCreateDto advertisementCreateData) {
        Advertisement advertisement = new Advertisement();
        advertisement.setUserId(advertisementCreateData.getUserId());
        Date postDate = getCalendarInstance().getTime();
        advertisement.setPostDate(postDate);
        advertisement.setExpireDate(DateUtils.addMonths(postDate, 6));
        advertisement.setSold(false);
        advertisement.setViews(0);
        advertisement.setLocation(advertisementCreateData.getLocation());
        advertisement.setAuctionPossible(advertisementCreateData.isAuctionPossible());
        advertisement.setExchangePossible(advertisementCreateData.isExchangePossible());
        advertisement.setExchangeForRealtyPossible(advertisementCreateData.isExchangeForRealtyPossible());
        advertisement.setPrice(advertisementCreateData.getPrice());
        advertisement.setCar(advertisementCreateData.getCar());
        advertisement.setModerated(false);

        mongoTemplate.insert(advertisement, COLLECTION_NAME);

        return advertisement;
    }

    @Override
    public long delete(String userId, String advertisementId) {
        Query query = new Query();
        query.addCriteria(
                Criteria
                        .where("userId")
                        .is(userId)
                        .and("_id")
                        .is(advertisementId)
        );

        return mongoTemplate.remove(query, COLLECTION_NAME).getDeletedCount();
    }

    @Override
    public AdvertisementResponseDto get(String advertisementId) {
        return mongoTemplate
                .findById(
                        advertisementId,
                        AdvertisementResponseDto.class,
                        COLLECTION_NAME
                );
    }

    @Override
    public List<AdvertisementResponseDto> getUserAdvertisements(String userId) {
        Query query = new Query();
        query.addCriteria(
                Criteria
                        .where("userId")
                        .is(userId)
        );
        return mongoTemplate.find(
                query,
                AdvertisementResponseDto.class,
                COLLECTION_NAME
        );
    }

    @Override
    public void incrementAdvertisementViews(String advertisementId) {
        Query query = new Query();
        query.addCriteria(
                Criteria
                        .where("advertisementId")
                        .is(advertisementId)
        );

        int views = getAdvertisementViews(advertisementId);

        Update update = new Update();
        update.set("views", views + 1);

        mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
    }

    @Override
    public int getAdvertisementViews(String advertisementId) {
        Query query = new Query();
        query.addCriteria(
                Criteria
                        .where("advertisementId")
                        .is(advertisementId)
        );

        Advertisement advertisement = mongoTemplate.findOne(query, Advertisement.class);

        if (advertisement != null) {
            return advertisement.getViews();
        } else {
            return -1;
        }
    }

    private Calendar getCalendarInstance() {
        return Calendar.getInstance(TimeZone.getDefault());
    }
}
