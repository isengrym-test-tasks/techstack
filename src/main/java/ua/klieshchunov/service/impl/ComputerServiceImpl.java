package ua.klieshchunov.service.impl;

import ua.klieshchunov.exception.ComputerNotFoundException;
import ua.klieshchunov.model.entity.computer.Computer;
import ua.klieshchunov.model.entity.computer.ComputerType;
import ua.klieshchunov.repository.impl.ComputerRepositoryImpl;
import ua.klieshchunov.repository.EntityCrudRepository;
import ua.klieshchunov.service.ComputerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComputerServiceImpl implements ComputerService {
    private final EntityCrudRepository<Computer> computerRepo;

    private static ComputerServiceImpl instance;

    private ComputerServiceImpl() {
        computerRepo = ComputerRepositoryImpl.getInstance();
    }

    public static synchronized ComputerServiceImpl getInstance() {
        if (instance == null)
            instance = new ComputerServiceImpl();

        return instance;
    }

    @Override
    public Computer findById(int id) throws ComputerNotFoundException {
        Optional<Computer> computer = computerRepo.findById(id);
        return computer.orElseThrow(() -> new ComputerNotFoundException(String.format("Couldn't find computer with id='%s'", id)));
    }

    @Override
    public void throwIfNotExists(int id) throws ComputerNotFoundException {
        findById(id);
    }

    @Override
    public List<Computer> findAllAvailable() {
        return computerRepo.findAll().stream()
                .filter(this::isInStock)
                .collect(Collectors.toList());
    }

    private boolean isInStock(Computer computer) {
        return computer.getQuantityInStock() > 0;
    }

    @Override
    public List<Computer> findAllAvailable(ComputerType type) {
        if (Optional.ofNullable(type).isEmpty())
            return new ArrayList<>();

        List<Computer> computers = computerRepo.findAll();
        return computers.stream()
                .filter(computer -> type.equals(computer.getComputerType()))
                .collect(Collectors.toList());
    }
}
