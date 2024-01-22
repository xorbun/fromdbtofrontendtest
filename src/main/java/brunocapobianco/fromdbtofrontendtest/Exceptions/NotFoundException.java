package brunocapobianco.fromdbtofrontendtest.Exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException
{
    public NotFoundException(UUID id)
    {
        super("elemento con id "+ id + " non trovato");
    }
    public NotFoundException(String message)
    {
        super("L' elemento con email "+ message +" non è stato trovato");
    }
}
