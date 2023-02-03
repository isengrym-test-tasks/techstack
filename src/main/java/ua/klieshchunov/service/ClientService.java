package ua.klieshchunov.service;

import ua.klieshchunov.exception.ClientNotFoundException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.exception.EntityIllegalArgumentException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.service.impl.ClientServiceImpl;

public interface ClientService {
    Client findById(int id)
            throws ClientNotFoundException;
    Client register(Client client)
            throws EntityAlreadyExistsException;
    Client updateFunds(Client client, int amount, ClientServiceImpl.Operation operation)
            throws EntityIllegalArgumentException;
}
