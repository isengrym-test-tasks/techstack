package ua.klieshchunov.service.impl;

import ua.klieshchunov.exception.ClientNotFoundException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.repository.impl.ClientRepositoryImpl;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.service.ClientService;

public class ClientServiceImpl implements ClientService {
    private final EntityCrudRepository<Client> clientRepo;
    private static ClientServiceImpl instance;

    private ClientServiceImpl() {
        clientRepo = ClientRepositoryImpl.getInstance();
    }

    public static synchronized ClientServiceImpl getInstance() {
        if (instance == null)
            instance = new ClientServiceImpl();

        return instance;
    }

    @Override
    public Client findById(int id) throws ClientNotFoundException {
        return clientRepo.findById(id).orElseThrow(
                ()-> new ClientNotFoundException(String.format("Couldn't find client with id='%s'",id))
        );
    }

    @Override
    public void throwIfNotExists(int id) throws ClientNotFoundException {
        findById(id);
    }

    @Override
    public Client register(Client client) throws EntityAlreadyExistsException {
        return clientRepo.save(client);
    }
}
