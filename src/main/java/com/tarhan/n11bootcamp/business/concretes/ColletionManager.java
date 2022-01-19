package com.tarhan.n11bootcamp.business.concretes;

import com.tarhan.n11bootcamp.business.abstracts.CollectionService;
import com.tarhan.n11bootcamp.business.abstracts.DebtService;
import com.tarhan.n11bootcamp.dataAccess.abstracts.CollectionDao;
import com.tarhan.n11bootcamp.dataAccess.abstracts.DebtDao;
import com.tarhan.n11bootcamp.entity.Collection;
import com.tarhan.n11bootcamp.entity.Debt;
import com.tarhan.n11bootcamp.enums.DebtType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ColletionManager implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private DebtService debtService;

    @Override
    @Transactional
    public void doColletion(Long debtId) {
        //Optional<Debt> debt =debtDao.findById(debtId);
        Debt debt = debtService.getById(debtId);

        BigDecimal chargeOfDebt = BigDecimal.ZERO;
        if(debt.getExpiryDate().isBefore(LocalDate.now())){
            chargeOfDebt.add(debtService.delayChargeOfDebt(debt.getRemainingDebtAmount(),debt.getExpiryDate()));
            Debt debtForCharge = new Debt();

            debtForCharge.setDebtType(DebtType.LATE);
            debtForCharge.setCreateDate(LocalDate.now());
            debtForCharge.setParentDebtId(debtId);
            debtForCharge.setMainDebtAmount(chargeOfDebt);
            debtForCharge.setRemainingDebtAmount(BigDecimal.ZERO);
            debtForCharge.setExpiryDate(LocalDate.now());
            debtForCharge.setUser(debt.getUser());

            debtService.save(debtForCharge);
        }
        // tahsilat zamanı geçmişse yeni borç eklendi

        Collection collection =new Collection();
        collection.setAmount(debt.getRemainingDebtAmount());
        collection.setCollectionDate(LocalDate.now());
        collection.setDebt(debt);
        collectionDao.save(collection);

        //tahsilat tablosuna kayıt atıldı

        debt.setRemainingDebtAmount(BigDecimal.ZERO);
        debtService.save(debt);
        //asıl borçtaki kalan tutarı sıfır yap


    }

    @Override
    public List<Collection> getCollectionBetweenDates(LocalDate startDate, LocalDate finishDate) {
        return collectionDao.getCollectionBetweenDates(startDate,finishDate);
    }

    @Override
    public List<Collection> getCollectionsByUserID(Long userId) {
        return collectionDao.getCollectionsByUserID(userId);
    }

    @Override
    public List<Collection> getSumChargeByUserID(Long userId) {
        return collectionDao.getSumChargeByUserID(userId,DebtType.LATE);
    }
}
