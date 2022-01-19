package com.tarhan.n11bootcamp.controllers;

import com.tarhan.n11bootcamp.business.abstracts.CollectionService;
import com.tarhan.n11bootcamp.entity.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping("/doColletion/{debtId}")
    public void doColletion(@PathVariable Long debtId){
        collectionService.doColletion(debtId);
    }

    @GetMapping("/getCollectionBetweenDates/{startDate}/{finishDate}")
    public List<Collection> getCollectionBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate finishDate){
        return collectionService.getCollectionBetweenDates(startDate,finishDate);
    }

    @GetMapping("/getCollectionsByUserID/{userId}")
    List<Collection> getCollectionsByUserID(@PathVariable Long userId){
        return collectionService.getCollectionsByUserID(userId);
    }
    @GetMapping("/getSumChargeByUserID/{userId}")
    List<Collection> getSumChargeByUserID(@PathVariable Long userId){
        return collectionService.getSumChargeByUserID(userId);
    }


}
