package ua.klieshchunov.model.entity.computer;

import ua.klieshchunov.model.entity.computer.components.*;

//The only reason why IT exists is because I decided not to use DB in this task
public class ComputerFactory {
    public static Computer getPersonalComputer(int id, int quantity, int price, String brand) {
        PersonalComputer computer = new PersonalComputer();
        computer.setId(id);
        computer.setQuantityInStock(quantity);
        computer.setBrand(brand);
        computer.setPriceUSD(price);
        computer.setComputerCase(
                (ComputerCase) ComponentFactory.getComponent(1,"BeQuite Pure Base 500", ComputerCase.class)
        );
        computer.setOs(
                (OperationalSystem) ComponentFactory.getComponent(2,"Windows 10 Home",  OperationalSystem.class)
        );
        computer.setMotherboard(
                (Motherboard) ComponentFactory.getComponent(3,  "B450",  Motherboard.class)
        );
        computer.setCpu(
                (Cpu) ComponentFactory.getComponent(4, "AMD Ryzen 7 5800X", Cpu.class)
        );
        computer.setGpu(
                (Gpu) ComponentFactory.getComponent(5, "MSI 2070", Gpu.class)
        );
        computer.setRam(
                (Ram) ComponentFactory.getComponent(6, "Kingston Fury DDR4-3200 16384 MB", Ram.class)
        );
        return computer;
    }

    public static Computer getServerComputer(int id, int quantity, int price, String brand) {
        ServerComputer computer = new ServerComputer();
        computer.setId(id);
        computer.setQuantityInStock(quantity);
        computer.setBrand(brand);
        computer.setPriceUSD(price);
        computer.setConnectionInterface("SATA");
        computer.setFormFactor("2U");
        computer.setOs(
                (OperationalSystem) ComponentFactory.getComponent(7,"Ubuntu Linux",  OperationalSystem.class)
        );
        computer.setMotherboard(
                (Motherboard) ComponentFactory.getComponent(8,  "PRIME B550M-A",  Motherboard.class)
        );
        computer.setCpu(
                (Cpu) ComponentFactory.getComponent(9, "2 x Intel XEON Quad Core E5-2609 2.40GHz (SR0LA) 80 W", Cpu.class)
        );
        computer.setGpu(
                (Gpu) ComponentFactory.getComponent(10, "Integrated", Gpu.class)
        );
        computer.setRam(
                (Ram) ComponentFactory.getComponent(11, "8GB (4х2GB) DDR3 ECC Registered", Ram.class)
        );
        return computer;
    }

    public static Computer getLaptop(int id, int quantity, int price, String brand) {
        Laptop computer = new Laptop();
        computer.setId(id);
        computer.setQuantityInStock(quantity);
        computer.setBrand(brand);
        computer.setPriceUSD(price);
        computer.setBattery(
                (Battery) ComponentFactory.getComponent(12,"70 Watt per Hour", Battery.class)
        );
        computer.setOs(
                (OperationalSystem) ComponentFactory.getComponent(13,"macOS Ventura",  OperationalSystem.class)
        );
        computer.setDisplaySpecs(
                (Display) ComponentFactory.getComponent(14,"Liquid Retina XDR", Display.class)
        );
        computer.setMotherboard(
                (Motherboard) ComponentFactory.getComponent(15,  "motherboard290-PRO",  Motherboard.class)
        );
        computer.setCpu(
                (Cpu) ComponentFactory.getComponent(16, "M1", Cpu.class)
        );
        computer.setGpu(
                (Gpu) ComponentFactory.getComponent(17, "Apple M2 Pro Graphics", Gpu.class)
        );
        computer.setRam(
                (Ram) ComponentFactory.getComponent(18, "16 GB", Ram.class)
        );
        return computer;
    }

}
