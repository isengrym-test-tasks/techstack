package ua.klieshchunov.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Entity {
    @EqualsAndHashCode.Include
    private int id;
}
