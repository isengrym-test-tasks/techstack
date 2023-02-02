package ua.klieshchunov.model.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Entity {
    private int clientId;
    private int computerId;


    public Order(int id) {
        super(id);
    }

    public Order(int id, int clientId, int computerId) {
        super(id);
        this.clientId = clientId;
        this.computerId = computerId;
    }
}
