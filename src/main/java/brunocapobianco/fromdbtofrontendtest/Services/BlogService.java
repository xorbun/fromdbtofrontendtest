package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.Blog;
import brunocapobianco.fromdbtofrontendtest.Exceptions.NotFoundException;
import brunocapobianco.fromdbtofrontendtest.Repositories.BlogDao;
import brunocapobianco.fromdbtofrontendtest.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService
{
    @Autowired
    private BlogDao blogdao;
    @Autowired
    private UserDao userDao;

    public Page<Blog> Getblog(int page,int size,String orderBy)
    {
        if(size>=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return blogdao.findAll(pageable);
    }
    public Blog findById(UUID id_blog)
    {
        return blogdao.findById(id_blog).orElseThrow(()->new NotFoundException(id_blog));
    }
    public Blog save(Blog body,UUID id)
    {
        //dovrebbe prendersi l id dell utente
        body.setUser(userDao.findById(id).orElseThrow(()->new NotFoundException(id)));
        return blogdao.save(body);
    }
    public Blog findByIdAndUpdate(UUID id_blog,Blog body)
    {
        Blog found=this.findById(id_blog);
        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setCover(body.getCover());
        found.setTempodilettura(body.getTempodilettura());
        return blogdao.save(found);
    }
    public void findByIdAndDelete(UUID id_blog)
    {
        Blog found=this.findById(id_blog);
        blogdao.delete(found);
    }
}
