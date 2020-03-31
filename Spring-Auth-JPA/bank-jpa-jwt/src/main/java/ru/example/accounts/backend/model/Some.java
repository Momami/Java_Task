package ru.example.accounts.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "some")
@NoArgsConstructor
public class Some {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "currency")
    private String currency;

    public Some(String currency) {
        this.amount = 0;
        this.currency = currency;
    }
}
