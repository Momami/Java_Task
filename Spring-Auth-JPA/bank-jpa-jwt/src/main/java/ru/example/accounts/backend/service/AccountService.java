package ru.example.accounts.backend.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.dao.UserRepository;
import ru.example.accounts.backend.model.Account;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void save(Account account){

        accountRepository.save(account);
    }
}
