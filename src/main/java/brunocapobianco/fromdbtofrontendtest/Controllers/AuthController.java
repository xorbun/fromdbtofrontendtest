package brunocapobianco.fromdbtofrontendtest.Controllers;

import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginDTO;
import brunocapobianco.fromdbtofrontendtest.Payloads.UserLoginResponseDTO;
import brunocapobianco.fromdbtofrontendtest.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body)
    {
        String accesToken=authService.Authenticator(body);
        return new UserLoginResponseDTO(accesToken);
    }
}
