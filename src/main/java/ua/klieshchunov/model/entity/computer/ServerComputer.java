package ua.klieshchunov.model.entity.computer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerComputer extends Computer {
    private String formFactor;
    private String connectionInterface;

    @Override
    public ComputerType getComputerType() {
        return ComputerType.SERVER;
    }
}
