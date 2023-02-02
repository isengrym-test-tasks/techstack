package ua.klieshchunov.model.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client extends Entity {
    private String name;
    private String surname;
    private String email;

    public Client(int id) {
        super(id);
    }

    public Client(int id, String name, String surname, String email) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
