package com.tarhan.n11bootcamp.dataAccess.abstracts;

import com.tarhan.n11bootcamp.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DebtDao extends JpaRepository<Debt,Long> {
    @Query("select debts from Debt debts where debts.createDate >= :startDate and debts.createDate <= :finishDate")
    List<Debt> findDebtBetweenDates(@Param("startDate")LocalDate startDate, @Param("finishDate")LocalDate finishDate);

    @Query("select debts from Debt debts where debts.user.id = :userId and debts.remainingDebtAmount > :zero")
    List<Debt> findDebtByUser(@Param("userId") Long userId,@Param("userId") BigDecimal zero);

    @Query("select debts from Debt debts where debts.user.id = :userId and debts.expiryDate < :nowDate ")
    List<Debt> findOverdueDebtByUser(@Param("userId") Long userId,@Param("nowDate")LocalDate nowDate);

    @Query("select SUM(debts.remainingDebtAmount) from Debt debts where debts.user.id = :userId ")
    BigDecimal findSumDebtOfUser(@Param("userId") Long userId);

    @Query("select SUM(debts.remainingDebtAmount) from Debt debts where debts.user.id = :userId and debts.expiryDate < :nowDate ")
    BigDecimal findSumOverdueDebtOfUser(@Param("userId") Long userId,@Param("nowDate") LocalDate nowDate);
}
