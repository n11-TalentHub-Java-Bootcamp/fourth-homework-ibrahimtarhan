package com.tarhan.n11bootcamp.business.concretes;

import com.tarhan.n11bootcamp.business.abstracts.DebtService;
import com.tarhan.n11bootcamp.dataAccess.abstracts.DebtDao;
import com.tarhan.n11bootcamp.entity.Debt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class DebtManager implements DebtService {
    @Autowired
    private DebtDao debtDao;

    @Override
    public BigDecimal CurrentSumDelayChargeByUser(Long userId) {
        Date date1 = new Date();
        BigDecimal sumDelayCharge = BigDecimal.ZERO;
        List<Debt> overdueDebtList =debtDao.findOverdueDebtByUser(userId, LocalDate.now());
        for(int i =0;i<overdueDebtList.size();i++){
            sumDelayCharge = sumDelayCharge.add(delayChargeOfDebt(
                    overdueDebtList.get(i).getRemainingDebtAmount()
                    ,overdueDebtList.get(i).getExpiryDate()));
        }

        return sumDelayCharge;
    }

    @Override
    public Debt getById(Long id) {
        return null;
    }
    @Override
    public BigDecimal delayChargeOfDebt(BigDecimal amount, LocalDate overdueDate){
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            //String dateString = format.format( new Date()   );
//            try {
//                Date dateFirst   = format.parse( "2018-01-01" );
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            BigDecimal charge = BigDecimal.ZERO;
            if(overdueDate.isBefore(LocalDate.parse("2018-01-01"))){
                int monthsBetween = (int) ChronoUnit.MONTHS.between(
                        YearMonth.from(overdueDate),
                        YearMonth.from(LocalDate.parse("2018-01-01"))
                );
                for(int i =1;i<= monthsBetween;i++){
                    charge = charge.add(amount.multiply(BigDecimal.valueOf(1.5)));
                }
            }
            else{
                int monthsBetween = (int) ChronoUnit.MONTHS.between(
                        YearMonth.from(LocalDate.parse("2018-01-01")),
                        YearMonth.from(LocalDate.now())
                );
                for(int i =1;i<= monthsBetween;i++){
                    charge = charge.add(amount.multiply(BigDecimal.valueOf(2.0)));
                }
            }
            if (BigDecimal.ONE.compareTo(charge)==1){
                charge = BigDecimal.ONE;
            }

        return  charge;
        }

    @Override
    public Debt save(Debt debt) {
        return debtDao.save(debt);
    }

    @Override
    public void update(Debt debt) {
        //debtDao.
    }

    @Override
    public List<Debt> getDebtBetweenDates(LocalDate startDate, LocalDate finishDate) {
        return debtDao.findDebtBetweenDates(startDate,finishDate);
    }

    @Override
    public List<Debt> getAllDebtsByUser(Long userId) {
        return debtDao.findDebtByUser(userId,BigDecimal.ZERO);//kullanıcının kalan borç tutarı sıfırdan buyuk olan borçlar
    }

    @Override
    public List<Debt> getOverdueDebtByUser(Long UserId) {
        return debtDao.findOverdueDebtByUser(UserId,LocalDate.now());
    }

    @Override
    public BigDecimal getSumDebtOfUser(Long userId) {
        return debtDao.findSumDebtOfUser(userId);
    }

    @Override
    public BigDecimal getSumOverdueDebtOfUser(Long userId) {
        return debtDao.findSumOverdueDebtOfUser(userId,LocalDate.now());
    }
}
