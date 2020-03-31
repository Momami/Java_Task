package ru.example.accounts.backend.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.dao.UserRepository;
import ru.example.accounts.backend.model.Account;
import ru.example.accounts.backend.model.Operation;
import ru.example.accounts.backend.model.User;

import java.util.List;

/**
 * The  Account service class.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * Get account by phone.
     *
     * @param phone the phone
     * @return the account
     */
    public User get(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User getThroughName(String username) {
        return userRepository.findByLogin(username);
    }
    /**
     * Delete account operation.
     *
     * @param phone the phone
     * @return the account
     */
    public void delete(String phone) {
        User account = userRepository.findByPhone(phone);
        userRepository.delete(account);
    }



    /**
     * Get all list accounts.
     *
     * @return the list
     */
    public List<User> getAll(){
        return Lists.newArrayList( userRepository.findAll());
    }
}
