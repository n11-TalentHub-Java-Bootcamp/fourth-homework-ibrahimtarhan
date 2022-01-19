package com.tarhan.n11bootcamp.controllers;

import com.tarhan.n11bootcamp.business.abstracts.DebtService;
import com.tarhan.n11bootcamp.entity.Debt;
import com.tarhan.n11bootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/debts")
public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping("/add")
    public Debt AddUser(@RequestBody Debt debt){
        return debtService.save(debt);
    }

    @GetMapping("/getDebtBetweenDates/{startDate}/{finishDate}")
    public List<Debt> getDebtBetweenDates(@PathVariable LocalDate startDate,@PathVariable LocalDate finishDate){
        return debtService.getDebtBetweenDates(startDate,finishDate);
    }
    @GetMapping("/getAllDebtsByUser/{userId}")
    List<Debt> getAllDebtsByUser(@PathVariable Long userId){
        return debtService.getAllDebtsByUser(userId);
    }

    @GetMapping("/getOverdueDebtByUser/{userId}")
    List<Debt> getOverdueDebtByUser(@PathVariable Long userId){
        return debtService.getOverdueDebtByUser(userId);
    }

    @GetMapping("/getSumDebtOfUser/{userId}")
    BigDecimal getSumDebtOfUser(@PathVariable Long userId){
        return debtService.getSumDebtOfUser(userId);
    }

    @GetMapping("/getSumOverdueDebtOfUser/{userId}")
    BigDecimal getSumOverdueDebtOfUser(@PathVariable Long userId){
        return debtService.getSumOverdueDebtOfUser(userId);
    }

     @GetMapping("/getCurrentSumDelayChargeByUser/{userId}")
     BigDecimal getCurrentSumDelayChargeByUser(@PathVariable Long userId){
        return debtService.CurrentSumDelayChargeByUser(userId);
     }
}
