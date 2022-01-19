package com.tarhan.n11bootcamp.business.abstracts;

import com.tarhan.n11bootcamp.entity.Debt;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DebtService {
    BigDecimal CurrentSumDelayChargeByUser(Long userId);

    Debt getById(Long id);

    BigDecimal delayChargeOfDebt(BigDecimal amount, LocalDate overdueDate);
    Debt save(Debt debt);

    void update(Debt debt);

    List<Debt> getDebtBetweenDates(LocalDate startDate, LocalDate finishDate);
    List<Debt> getAllDebtsByUser(Long userId);
    List<Debt> getOverdueDebtByUser(Long userId);

    BigDecimal getSumDebtOfUser(Long userId);

    BigDecimal getSumOverdueDebtOfUser(Long userId);

}
