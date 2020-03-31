package ru.example.accounts.backend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.example.accounts.backend.model.Account;
import ru.example.accounts.backend.model.Operation;
import ru.example.accounts.backend.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhone(String phone);
    User findByLogin(String login);
    boolean existsUserByLogin(String login);
    boolean existsUserByPhone(String phone);
}
