package ru.example.accounts.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Account entity model.
 */
@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column
    private String accCode;
    @Column(name = "defAccount")
    private boolean defAccount = false;

    // TO DO Operation
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Operation> operationList = new HashSet<>();

    public Account() {
        this.amount = new BigDecimal(0);
        this.accCode = "RUB";
    }

    public Account(String accCode) {
        this.amount = new BigDecimal(0);
        this.accCode = accCode;
    }

    public Account(BigDecimal amount, String accCode, boolean defAccount) {
        this.amount = amount;
        this.accCode = accCode;
        this.defAccount = defAccount;
    }

    public void setOperationList(Operation operation) {
        if ( this.operationList ==null){
            this.operationList = new HashSet<>();
        }
        operationList.add(operation);
    }
}
