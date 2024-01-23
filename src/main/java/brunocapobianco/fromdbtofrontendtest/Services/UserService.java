package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.Role;
import brunocapobianco.fromdbtofrontendtest.Entities.User;
import brunocapobianco.fromdbtofrontendtest.Exceptions.NotFoundException;
import brunocapobianco.fromdbtofrontendtest.Payloads.NewUserDTO;
import brunocapobianco.fromdbtofrontendtest.Repositories.UserDao;
import brunocapobianco.fromdbtofrontendtest.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.UUID;

@Service
public class UserService
{

    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public Page<User>Getusers(int page,int size,String orderBy)
    {
        //return userDao.findAll();
        if(size>=100)size=100;
        Pageable pageable=PageRequest.of(page,size,Sort.by(orderBy));
        return userDao.findAll(pageable);
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
    public User findByIdAndUpdate(UUID id,NewUserDTO body)
    {
        User found=this.findById(id);
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setDatanascita(LocalDate.now());
        found.setPassword(bcrypt.encode(body.password()));
        found.setAvatar(body.avatar());
        return userDao.save(found);
    }
    public User findByemail(String email)
    {
        System.out.println("*******************************************"+ email);
        return userDao.findByEmail(email).orElseThrow(()->new NotFoundException(email));
    }
}
