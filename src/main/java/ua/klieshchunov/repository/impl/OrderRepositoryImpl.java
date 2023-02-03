package ua.klieshchunov.repository.impl;

import ua.klieshchunov.model.entity.Order;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.repository.StorageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements EntityCrudRepository<Order> {
    private static OrderRepositoryImpl instance;

    private List<Order> storage;

    private OrderRepositoryImpl() {
        this.storage = new ArrayList<>(Arrays.asList(
                new Order(1, 1, 5)
        ));
    }

    public static synchronized OrderRepositoryImpl getInstance() {
        if (instance == null)
            instance = new OrderRepositoryImpl();

        return instance;
    }

    @Override
    public Optional<Order> findById(int id) {
        return storage.stream()
                .filter(entity -> entity.getId() == id)
                .findAny();
    }

    @Override
    public List<Order> findAll() {
        return storage;
    }

    @Override
    public void update(Order entity) {
        int index = StorageUtils.getStorageIndex(storage, entity);
        storage.remove(index);
        storage.add(entity);
    }

    @Override
    public Order save(Order entity) {
        int id = StorageUtils.getAutoId(storage);
        entity.setId(id);
        storage.add(entity);
        return entity;
    }

    @Override
    public void delete(int id) {
        int storageIndex = StorageUtils.getStorageIndex(storage, new Order(id));
        storage.remove(storageIndex);
    }
}
