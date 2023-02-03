package ua.klieshchunov.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Client extends Entity {
    private String name;
    private String surname;
    private String email;
    private int fundsUsd;

    public Client(int id) {
        super(id);
    }

    public Client(int id, String name, String surname, String email, int fundsUsd) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.fundsUsd = fundsUsd;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() +'\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", fundsUsd=" + fundsUsd +
                '}';
    }
}
