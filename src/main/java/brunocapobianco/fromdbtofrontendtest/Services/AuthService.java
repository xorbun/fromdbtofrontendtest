package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.UnauthorizedException;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginDTO;
import brunocapobianco.fromdbtofrontendtest.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String Authenticator(UserLoginDTO body)
    {
        User user= userService.findByEmail(body.email());
        if(body.Password().equals(user.getPassword()))
        {
            return jwtTools.createToken(user);
        }
        else
        {
            throw new UnauthorizedException("password errata");
        }
    }
}
