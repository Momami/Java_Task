package ru.example.accounts.backend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.example.accounts.backend.model.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long> {
}
