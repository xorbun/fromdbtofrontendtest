package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.NotFoundException;
import brunocapobianco.fromdbtofrontendtest.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    public List<User>Getusers()
    {
        return userDao.findAll();
    }
    public User getUser(UUID id_user)
    {
        return userDao.findById(id_user).orElseThrow(()->new NotFoundException(id_user));
    }
    public User save(User body)
    {
        body.setDatanascita(LocalDate.now());
        return userDao.save(body);
    }
    public User findById(UUID id)
    {
        return userDao.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id)
    {
        User found=this.findById(id);
        userDao.delete(found);
    }
    public User findByIdAndUpdate(UUID id,User body)
    {
        User found=this.findById(id);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setDatanascita(body.getDatanascita());
        found.setAvatar(body.getAvatar());
        return userDao.save(found);
    }
}
