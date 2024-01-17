package brunocapobianco.fromdbtofrontendtest.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsPayload
{
    private String message;
    private LocalDateTime timestamp;
}
