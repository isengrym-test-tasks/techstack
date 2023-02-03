package ua.klieshchunov.repository.impl;

import ua.klieshchunov.exception.ClientAlreadyExistsException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.repository.StorageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements EntityCrudRepository<Client> {
    private static ClientRepositoryImpl instance;

    private List<Client> storage;

    private ClientRepositoryImpl() {
        this.storage = new ArrayList<>(Arrays.asList(
                new Client(1, "Alexey", "Klieshchunov", "alexeyklieshchunov@gmail.com", 100)
        ));
    }

    public static synchronized ClientRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ClientRepositoryImpl();

        return instance;
    }

    @Override
    public Optional<Client> findById(int id) {
        return storage.stream()
                .filter(entity -> entity.getId() == id)
                .findAny();
    }

    @Override
    public List<Client> findAll() {
        return storage;
    }

    @Override
    public void update(Client entity) {
        int index = StorageUtils.getStorageIndex(storage, entity);
        storage.remove(index);
        storage.add(entity);
    }

    @Override
    public Client save(Client client) throws ClientAlreadyExistsException {
        if (findById(client.getId()).isPresent())
            throw new ClientAlreadyExistsException(String.format("Client with id='%s' already exists", client.getId()));

        int generatedId = StorageUtils.getAutoId(storage);
        client.setId(generatedId);
        storage.add(client);

        return client;
    }


    @Override
    public void delete(int id) {
        int storageIndex = StorageUtils.getStorageIndex(storage, new Client(id));
        storage.remove(storageIndex);
    }
}
