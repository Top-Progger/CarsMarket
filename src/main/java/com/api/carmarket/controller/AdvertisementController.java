package com.api.carmarket.controller;

import com.api.carmarket.dto.AdvertisementCreateDto;
import com.api.carmarket.dto.AdvertisementResponseDto;
import com.api.carmarket.model.Advertisement;
import com.api.carmarket.service.AdvertisementService;
import com.api.carmarket.utils.validator.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertisement")
@AllArgsConstructor
@Slf4j
public class AdvertisementController {
    @Autowired
    private final AdvertisementService advertisementService;

    @Autowired
    private final Validator jsonSchemaValidator;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<Advertisement> create(@RequestBody AdvertisementCreateDto advertisementCreateData) {
        boolean isDataValid = jsonSchemaValidator.validate(advertisementCreateData,
                "classpath:schema/advertisement.json");
        System.out.println("Data valid:" + " " + isDataValid);

//        if (isDataValid) {
        Advertisement createdAdvertisement = advertisementService.create(advertisementCreateData);

        if (createdAdvertisement != null) {
            return new ResponseEntity<>(createdAdvertisement, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//        }
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

    @RequestMapping("/get/advertisementId/{advertisement-id}")
    public ResponseEntity<AdvertisementResponseDto> get(@PathVariable String advertisementId) {
        AdvertisementResponseDto response = advertisementService.get(advertisementId);

        if (response != null) {
            advertisementService.increaseAdvertisementViews(advertisementId);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/get/userAdvertisement/all/{user-id}")
    public ResponseEntity<List<AdvertisementResponseDto>> getAllUserAdvertisements(@PathVariable String userId) {
        List<AdvertisementResponseDto> response = advertisementService.getUserAdvertisements(userId);

        if (response != null) {
            if (!response.isEmpty()) {
                return new ResponseEntity<>(response, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
