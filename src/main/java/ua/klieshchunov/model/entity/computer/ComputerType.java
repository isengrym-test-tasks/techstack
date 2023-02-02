package ua.klieshchunov.model.entity.computer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ComputerType {
    PC("Personal computer"), SERVER ("Server"), LAPTOP("Laptop");

    private final String nameOnTag;
}
