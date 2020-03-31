package ru.example.accounts.backend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.example.accounts.backend.model.Some;

public interface SomeRepository extends CrudRepository<Some,Long> {


}
