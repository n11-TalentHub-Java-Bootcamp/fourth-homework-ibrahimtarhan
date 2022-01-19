package com.tarhan.n11bootcamp.entity;

import com.tarhan.n11bootcamp.enums.DebtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "DEBT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 19, scale = 2)
    private BigDecimal mainDebtAmount;
    @Column(precision = 19, scale = 2)
    private BigDecimal remainingDebtAmount;
    private DebtType debtType;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private Long parentDebtId;
    //private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}
