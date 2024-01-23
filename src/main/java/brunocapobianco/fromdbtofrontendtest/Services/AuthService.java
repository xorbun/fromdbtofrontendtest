package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.Role;
import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.BadRequestException;
import brunocapobianco.fromdbtofrontendtest.Exceptions.UnauthorizedException;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTO;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginDTO;
import brunocapobianco.fromdbtofrontendtest.Repositories.UserDao;
import brunocapobianco.fromdbtofrontendtest.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService
{
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticator(UserLoginDTO body)
    {
        User user= userService.findByemail(body.email());
        if(bcrypt.matches(body.password(),user.getPassword()))
        {
            return jwtTools.createToken(user);
        }
        else
        {
            throw new UnauthorizedException("password errata");
        }
    }
    public User save(NewUserDTO body)
    {
        userDao.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("L'email "+ user.getEmail() + " è già in uso");
        });
        User newuser=new User();
        newuser.setNome(body.nome());
        newuser.setCognome(body.cognome());
        newuser.setPassword(bcrypt.encode(body.password()));
        newuser.setEmail(body.email());
        newuser.setDatanascita(LocalDate.now());
        newuser.setAvatar("ifhewiofofnewofbewofeowbfoiewbf");
        newuser.setRole(Role.USER);
        return userDao.save(newuser);
    }

}
