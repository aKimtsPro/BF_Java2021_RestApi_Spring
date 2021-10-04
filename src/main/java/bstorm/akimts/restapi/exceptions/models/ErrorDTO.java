package bstorm.akimts.restapi.exceptions.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private String message;
    private Instant timestamp = Instant.now();

    public ErrorDTO(String message) {
        this.message = message;
    }
}
