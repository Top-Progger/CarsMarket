package com.api.carmarket.controller;

import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.model.Advertisement;
import com.api.carmarket.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advertisement")
@AllArgsConstructor
public class AdvertisementController {
    @Autowired
    private final AdvertisementService advertisementService;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<Advertisement> create(@RequestBody AdvertisementCreateDto advertisementCreateData) {
        Advertisement createdAdvertisement = advertisementService.create(advertisementCreateData);

        if (createdAdvertisement != null) {
            return new ResponseEntity<>(createdAdvertisement, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete/userId/{user-id}/advertisementId/{advertisement-id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("user-id") String userId,
                                         @PathVariable("advertisement-id") String advertisementId) {
        long deletedAdvertisementsCount = advertisementService.delete(userId, advertisementId);

        if (deletedAdvertisementsCount != -1) {
            return new ResponseEntity<>("Advertisements deleted: " + deletedAdvertisementsCount,
                    HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
