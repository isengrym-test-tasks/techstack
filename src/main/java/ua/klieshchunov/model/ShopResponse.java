package ua.klieshchunov.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ShopResponse {
    public ResponseStatus status;
    public String payload;

    public ShopResponse(ResponseStatus status, String payload) {
        this.status = status;
        this.payload = payload;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ResponseStatus {
        SUCCESS("Success"), FAILURE("Failure. Check payload for details");

        private final String statusMessage;
    }
}


