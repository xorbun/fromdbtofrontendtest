package brunocapobianco.fromdbtofrontendtest.Controllers;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public User getUser(@PathVariable UUID id_user)
    {
       return userservice.findById(id_user);
    }
    @PostMapping
    public User createUser(@RequestBody User newUserPayload)
    {
        return userservice.save(newUserPayload);
    }
    @PutMapping("/{id_user}")
    public User getUserByIdAndUpdate(@PathVariable UUID id_user,@RequestBody User modifyUserPayload)
    {
        return userservice.findByIdAndUpdate(id_user,modifyUserPayload);
    }
    @DeleteMapping("{id_user}")
    public void deleteUser(@PathVariable UUID id_user)
    {
        userservice.findByIdAndDelete(id_user);
    }
}
