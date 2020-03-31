package ru.example.accounts.backend.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.example.accounts.backend.model.Account;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Long> {
    boolean existsAccountByAccCode(String accCode);
    List<Account> findAccountsByDefAccount(boolean defAccount);
    boolean existsAccountByDefAccount(boolean defAccount);
    @Modifying
    @Query("UPDATE Account ac SET ac.defAccount = :defAccount WHERE ac.id = :id")
    void changeDefAccount(@Param("id") long id, @Param("defAccount") boolean defAccount);
}
