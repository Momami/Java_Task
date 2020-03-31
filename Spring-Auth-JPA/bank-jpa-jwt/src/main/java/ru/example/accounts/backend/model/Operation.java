package ru.example.accounts.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column
    private BigDecimal amountBefore;
    @Column
    private BigDecimal amountAfter;
    @Column
    private LocalDate dateOfOperation;
    @Column
    private String accCode;
    @Column
    private String phoneOut;

    public Operation(String phone, BigDecimal amount, BigDecimal amountBefore, BigDecimal amountAfter,
                     LocalDate dateOfOperation, String accCode){
        this.accCode = accCode;
        this.amount = amount;
        this.amountAfter = amountAfter;
        this.amountBefore = amountBefore;
        this.dateOfOperation = dateOfOperation;
        this.phoneOut = phone;
    }
}
