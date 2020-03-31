package ru.example.accounts.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The User entity model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "login")
    private String login;
    @Column
    private String address;
    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Account> accountList = new HashSet<>();

    public User(String phone, String login, String password, String address) {
        this.phone = phone;
        this.login = login;
        this.address = address;
        this.password = password;
    }

    public void setAccountList(Account account) {
        if ( this.accountList ==null){
            this.accountList = new HashSet<>();
        }
        accountList.add(account);
    }

    public boolean checkAccCodeInAccountList(String accCode){
        for (Account account: accountList){
            if (account.getAccCode().equals(accCode))
                return true;
        }
        return false;
    }

    public Account getAccountWithAccCode(String accCode){
        for (Account account: accountList){
            if (account.getAccCode().equals(accCode))
                return account;
        }
        return null;
    }

    public Account getDefaultAccount(){
        for (Account account: accountList){
            if (account.isDefAccount())
                return account;
        }
        return null;
    }
}
