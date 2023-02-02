package ua.klieshchunov.model.entity.computer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.klieshchunov.model.entity.Entity;
import ua.klieshchunov.model.entity.computer.components.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class Computer extends Entity {
    private int priceUSD;
    private int quantityInStock;
    private String brand;
    private Motherboard motherboard;
    private Cpu cpu;
    private Gpu gpu;
    private Ram ram;
    private OperationalSystem os;

    public Computer(int id) {
        super(id);
    }

    public abstract ComputerType getComputerType();

    @Override
    public String toString() {
        return String.format("\n%s:" +
                "\nId: %s" +
                "\nBrand: %s" +
                "\nSpecifications [" +
                "\n\tMotherboard: %s" +
                "\n\tCPU: %s" +
                "\n\tGPU: %s" +
                "\n\tRAM: %s" +
                "\n\tOS: %s" +
                "\n]," +
                "\nPrice: %s$",
                getComputerType().getNameOnTag(), getId(), getBrand(), motherboard,
                cpu, gpu, ram, os, getPriceUSD());
    }
}
