package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.UnauthorizedException;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private UserService userService;

    public String Authenticator(UserLoginDTO body)
    {
        User user= userService.findByEmail(body.email());
        if(body.Password().equals(user.getPassword()))
        {
            return "token";
        }
        else
        {
            throw new UnauthorizedException("password errata");
        }
    }
}
