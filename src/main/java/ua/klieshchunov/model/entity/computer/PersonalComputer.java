package ua.klieshchunov.model.entity.computer;

import lombok.Getter;
import lombok.Setter;
import ua.klieshchunov.model.entity.computer.components.ComputerCase;

@Getter
@Setter
public class PersonalComputer extends Computer {
    private ComputerCase computerCase;

    @Override
    public ComputerType getComputerType() {
        return ComputerType.PC;
    }

}
