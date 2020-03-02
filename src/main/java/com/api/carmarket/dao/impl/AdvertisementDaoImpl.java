package com.api.carmarket.dao.impl;

import com.api.carmarket.dao.AdvertisementDao;
import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.model.Advertisement;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
public class AdvertisementDaoImpl implements AdvertisementDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Advertisement create(AdvertisementCreateDto advertisementCreateData) {
        Advertisement advertisement = new Advertisement();
        advertisement.setUserId(advertisementCreateData.getUserId());
        Date postDate = new Date();
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

        mongoTemplate.save(advertisement, "advertisements");

        return advertisement;
    }

    @Override
    public long delete(String userId, String advertisementId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("_id").is(advertisementId));

        return mongoTemplate.remove(query, "advertisements").getDeletedCount();
    }
}
