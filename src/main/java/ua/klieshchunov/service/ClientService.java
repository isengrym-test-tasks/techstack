package ua.klieshchunov.service;

import ua.klieshchunov.exception.ClientNotFoundException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.model.entity.Client;

public interface ClientService {
    Client findById(int id) throws ClientNotFoundException;
    void throwIfNotExists(int id) throws ClientNotFoundException;
    Client register(Client client) throws EntityAlreadyExistsException;
}
