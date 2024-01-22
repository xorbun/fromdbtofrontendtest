package brunocapobianco.fromdbtofrontendtest.Controllers;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.BadRequestException;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTO;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTOResponse;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginDTO;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginResponseDTO;
import brunocapobianco.fromdbtofrontendtest.Services.AuthService;
import brunocapobianco.fromdbtofrontendtest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body)
    {
        String accesToken=authService.authenticator(body);
        return new UserLoginResponseDTO(accesToken);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserDTOResponse createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation)
    {
        System.out.println(validation);
        if (validation.hasErrors())
        {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        }
        else
        {
            User newUser = userService.save(newUserPayload);
            return new NewUserDTOResponse(newUser.getId_user());
        }
    }
}
