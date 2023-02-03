package ua.klieshchunov;

import ua.klieshchunov.exception.EntityIllegalArgumentException;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.model.entity.Order;
import ua.klieshchunov.model.ShopResponse;
import ua.klieshchunov.model.entity.computer.Computer;
import ua.klieshchunov.model.entity.computer.ComputerType;
import ua.klieshchunov.service.*;
import ua.klieshchunov.service.impl.ClientServiceImpl;
import ua.klieshchunov.service.impl.ComputerServiceImpl;
import ua.klieshchunov.service.impl.OrderServiceImpl;

import java.util.List;

public class Shop {
    private final ComputerService computerService = ComputerServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    public ShopResponse getAvailableComputers() {
        List<Computer> computers = computerService.findAllAvailable();
        return createResponse(ShopResponse.ResponseStatus.SUCCESS, computers.toString());
    }

    public ShopResponse getAvailableComputers(ComputerType type) {
        List<Computer> specificComputers =  computerService.findAllAvailable(type);
        return createResponse(ShopResponse.ResponseStatus.SUCCESS, specificComputers.toString());
    }

    public ShopResponse createNewClient(Client client) {
        try {
            Client registeredClient = clientService.register(client);
            return createResponse(ShopResponse.ResponseStatus.SUCCESS, registeredClient.toString());
        } catch (Exception e) {
            return createResponse(ShopResponse.ResponseStatus.FAILURE, e.getMessage());
        }
    }

    public ShopResponse addFunds(Client client, int amount) {
        try {
            Client updatedFunds = clientService.updateFunds(client, amount, ClientServiceImpl.Operation.ADDITION);
            return createResponse(ShopResponse.ResponseStatus.SUCCESS, updatedFunds.toString());
        } catch (EntityIllegalArgumentException e) {
            return createResponse(ShopResponse.ResponseStatus.FAILURE, e.getMessage());
        }
    }

    public ShopResponse buyComputer(int clientId, int productId) {
        try {
            Order createdOrder = orderService.createOrder(clientId, productId);
            return createResponse(ShopResponse.ResponseStatus.SUCCESS, createdOrder.toString());
        } catch (Exception e) {
            return createResponse(ShopResponse.ResponseStatus.FAILURE, e.getMessage());
        }
    }

    private static ShopResponse createResponse(ShopResponse.ResponseStatus failure, String e) {
        return new ShopResponse(failure, e);
    }
}
