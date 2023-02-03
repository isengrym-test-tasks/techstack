package ua.klieshchunov.model.entity.computer;

import lombok.Getter;
import lombok.Setter;
import ua.klieshchunov.model.entity.computer.components.Battery;
import ua.klieshchunov.model.entity.computer.components.Display;

@Getter
@Setter
public class Laptop extends Computer {
    private Display displaySpecs;
    private Battery battery;

    @Override
    public ComputerType getComputerType() {
        return ComputerType.LAPTOP;
    }
}
