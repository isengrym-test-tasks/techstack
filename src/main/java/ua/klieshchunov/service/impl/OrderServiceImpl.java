package ua.klieshchunov.service.impl;

import ua.klieshchunov.exception.ClientNotFoundException;
import ua.klieshchunov.exception.ComputerNotFoundException;
import ua.klieshchunov.exception.EntityAlreadyExistsException;
import ua.klieshchunov.model.entity.Order;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.repository.impl.OrderRepositoryImpl;
import ua.klieshchunov.service.ClientService;
import ua.klieshchunov.service.ComputerService;
import ua.klieshchunov.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final EntityCrudRepository<Order> orderRepository;
    private final ClientService clientService;
    private final ComputerService computerService;
    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
        orderRepository = OrderRepositoryImpl.getInstance();
        clientService = ClientServiceImpl.getInstance();
        computerService = ComputerServiceImpl.getInstance();
    }

    public static synchronized OrderServiceImpl getInstance() {
        if (instance == null)
            instance = new OrderServiceImpl();

        return instance;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllForClient(int clientId) {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream()
                .filter(order -> order.getClientId() == clientId)
                .collect(Collectors.toList());
    }

    @Override
    public Order createOrder(int clientId, int computerId)
            throws EntityAlreadyExistsException, ClientNotFoundException, ComputerNotFoundException {

        clientService.throwIfNotExists(clientId);
        computerService.throwIfNotExists(computerId);

        Order order = new Order(clientId, computerId);
        return orderRepository.save(order);
    }
}
