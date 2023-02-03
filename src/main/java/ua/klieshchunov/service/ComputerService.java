package ua.klieshchunov.service;

import ua.klieshchunov.exception.ComputerNotFoundException;
import ua.klieshchunov.model.entity.computer.Computer;
import ua.klieshchunov.model.entity.computer.ComputerType;

import java.util.List;

public interface ComputerService {
    Computer findById(int id) throws ComputerNotFoundException;
    List<Computer> findAllAvailable();
    List<Computer> findAllAvailable(ComputerType filter);


}
