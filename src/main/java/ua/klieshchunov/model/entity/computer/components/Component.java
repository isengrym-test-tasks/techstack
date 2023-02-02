package ua.klieshchunov.model.entity.computer.components;

import lombok.*;
import ua.klieshchunov.model.entity.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Component extends Entity {
    private int priceUsd;
    private String brand;
    private String specs;
    private int quantityInStock;
}
