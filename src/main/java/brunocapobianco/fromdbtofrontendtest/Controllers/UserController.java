package brunocapobianco.fromdbtofrontendtest.Controllers;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTO;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTOResponse;
import brunocapobianco.fromdbtofrontendtest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Users")
public class UserController
{
    @Autowired
    private UserService userservice;

    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10")int size,
                               @RequestParam(defaultValue = "nome")String orderBy)
    {
        return userservice.Getusers(page,size,orderBy);
    }
    @GetMapping("/{id_user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(@PathVariable UUID id_user)
    {
       return userservice.findById(id_user);
    }

    @PutMapping("/{id_user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserByIdAndUpdate(@PathVariable UUID id_user,@RequestBody User modifyUserPayload)
    {
        return userservice.findByIdAndUpdate(id_user,modifyUserPayload);
    }
    @DeleteMapping("{id_user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable UUID id_user)
    {
        userservice.findByIdAndDelete(id_user);

    }
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser)
    {
        return currentUser;
    }
    @PutMapping("/me")
    public User getMeAndUpdate(@AuthenticationPrincipal User currentUser, @RequestBody User body)
    {
        return userservice.findByIdAndUpdate(currentUser.getId_user(), body);
    }
    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal User currentUser)
    {
        userservice.findByIdAndDelete(currentUser.getId_user());
    }
}
