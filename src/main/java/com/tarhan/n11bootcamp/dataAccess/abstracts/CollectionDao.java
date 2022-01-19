package com.tarhan.n11bootcamp.dataAccess.abstracts;

import com.tarhan.n11bootcamp.entity.Collection;
import com.tarhan.n11bootcamp.enums.DebtType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<Collection,Long> {
    //void doPayment(Long debtId);

    @Query("select collection from Collection collection where collection.collectionDate >= :startDate and collection.collectionDate <= :finishDate")
    List<Collection> getCollectionBetweenDates(LocalDate startDate, LocalDate finishDate);

    @Query("select collection from Collection collection where collection.debt.user.id = :userId")
    List<Collection> getCollectionsByUserID(Long userId);

    @Query("select collection from Collection collection where collection.debt.user.id = :userId and collection.debt.debtType = :debtType")
    List<Collection> getSumChargeByUserID(Long userId, DebtType debtType);
}
