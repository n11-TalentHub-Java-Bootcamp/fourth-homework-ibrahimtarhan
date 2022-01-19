package com.tarhan.n11bootcamp.business.abstracts;

import com.tarhan.n11bootcamp.entity.Collection;
import com.tarhan.n11bootcamp.enums.DebtType;

import java.time.LocalDate;
import java.util.List;

public interface CollectionService {
    void doColletion(Long debtId);
    List<Collection> getCollectionBetweenDates(LocalDate startDate, LocalDate finishDate);
    List<Collection> getCollectionsByUserID(Long userId);
    List<Collection> getSumChargeByUserID(Long userId);
}
