package brunocapobianco.fromdbtofrontendtest.Payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
