package ua.klieshchunov.repository.impl;

import ua.klieshchunov.exception.ComputerAlreadyExistsException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.model.entity.computer.Computer;
import ua.klieshchunov.model.entity.computer.ComputerFactory;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.repository.StorageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ComputerRepositoryImpl implements EntityCrudRepository<Computer> {
    private static ComputerRepositoryImpl instance;
    private List<Computer> storage;

    private ComputerRepositoryImpl() {
        this.storage = new ArrayList<>(Arrays.asList(
                ComputerFactory.getPersonalComputer(1, 10, 800, "RAZER"),
                ComputerFactory.getServerComputer(2, 4, 1400, "HP"),
                ComputerFactory.getPersonalComputer(3, 14, 900, "MSI"),
                ComputerFactory.getPersonalComputer(4, 14, 900, "MSI"),
                ComputerFactory.getLaptop(5, 67, 400, "Lenovo"),
                ComputerFactory.getLaptop(6, 3, 650, "Dell"),
                ComputerFactory.getLaptop(7, 21, 900, "Apple")
        ));
    }

    public static synchronized ComputerRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ComputerRepositoryImpl();

        return instance;
    }

    @Override
    public Optional<Computer> findById(int id) {
        return storage.stream()
                .filter(entity -> id == entity.getId())
                .findAny();
    }

    @Override
    public List<Computer> findAll() {
        return storage;
    }

    @Override
    public void update(Computer entity) {
        int index = StorageUtils.getStorageIndex(storage, entity);
        storage.remove(index);
        storage.add(entity);
    }

    @Override
    public Computer save(Computer entity) throws EntityAlreadyExistsException {
        if (findById(entity.getId()).isPresent())
            throw new ComputerAlreadyExistsException(String.format("Computer with id='%s' already exists", entity.getId()));

        int generatedId = StorageUtils.getAutoId(storage);
        entity.setId(generatedId);
        storage.add(entity);

        return entity;
    }

    @Override
    public void delete(int id) {
        int storageIndex = StorageUtils.getStorageIndex(storage, new Client(id));
        storage.remove(storageIndex);
    }
}
