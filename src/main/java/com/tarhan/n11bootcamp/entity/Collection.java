package com.tarhan.n11bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "COLLECTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate collectionDate;
    @Column(precision = 19, scale = 2)
    private BigDecimal amount;
    //private Long debtId;

    @OneToOne
    @JoinColumn(name = "debt_id", referencedColumnName = "id")
    private Debt debt;

}
