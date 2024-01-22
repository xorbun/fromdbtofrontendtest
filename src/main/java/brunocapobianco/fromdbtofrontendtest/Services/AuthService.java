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
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticator(UserLoginDTO body)
    {
        User user= userService.findByemail(body.email());
        System.out.println("*********************************"+ user);
        if(body.password().equals(user.getPassword()))
        {
            return jwtTools.createToken(user);
        }
        else
        {
            throw new UnauthorizedException("password errata");
        }
    }
}
