package ua.klieshchunov.service.impl;

import ua.klieshchunov.exception.ClientNotFoundException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.exception.EntityIllegalArgumentException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.repository.impl.ClientRepositoryImpl;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.service.ClientService;

import java.util.Optional;

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
    public Client register(Client client) throws EntityAlreadyExistsException {
        return clientRepo.save(client);
    }

    @Override
    public Client updateFunds(Client client, int amount, Operation operation) throws EntityIllegalArgumentException {
        if (Optional.ofNullable(client).isEmpty()) {
            return client;
        }

        int newAmount = client.getFundsUsd();

        if (Operation.ADDITION.equals(operation))
            newAmount = client.getFundsUsd() + amount;

        else if (Operation.SUBTRACTION.equals(operation))
            newAmount = client.getFundsUsd() - amount;

        if (newAmount < 0)
            throw new EntityIllegalArgumentException(
                    String.format("Client with id='%s' doesn't have enough money for subtraction operation", client.getId())
            );

        client.setFundsUsd(newAmount);
        clientRepo.update(client);
        return client;
    }

    public enum Operation {
        ADDITION, SUBTRACTION
    }

}
