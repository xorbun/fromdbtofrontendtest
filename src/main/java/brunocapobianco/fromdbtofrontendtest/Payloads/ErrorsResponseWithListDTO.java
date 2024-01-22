package brunocapobianco.fromdbtofrontendtest.Payloads;

import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(String message, Date timestamp, List<String> errorList) {
}
