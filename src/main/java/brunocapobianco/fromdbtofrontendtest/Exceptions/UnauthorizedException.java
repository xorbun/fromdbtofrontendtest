package brunocapobianco.fromdbtofrontendtest.Exceptions;

public class UnauthorizedException extends RuntimeException
{
    public UnauthorizedException(String message)
    {
        super(message);
    }
}
