package ua.klieshchunov.repository;

import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityCrudRepository<T extends Entity> {
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    T save(T entity) throws EntityAlreadyExistsException;
    void delete(int id);
}
