package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.NotFoundException;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTO;
import brunocapobianco.fromdbtofrontendtest.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.UUID;

@Service
public class UserService
{

    @Autowired
    private UserDao userDao;

    public Page<User>Getusers(int page,int size,String orderBy)
    {
        //return userDao.findAll();
        if(size>=100)size=100;
        Pageable pageable=PageRequest.of(page,size,Sort.by(orderBy));
        return userDao.findAll(pageable);
    }

    public User save(NewUserDTO body)
    {
        User newUser=new User();
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setEmail(body.email());
        newUser.setDatanascita(LocalDate.now());
        newUser.setAvatar("eoufhbeworughiowpefhwopeihgf");
        return userDao.save(newUser);
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
    public User findByEmail(String email)throws NotFoundException
    {
        return userDao.findByemail(email).orElseThrow(()->new NotFoundException(email));
    }
}
