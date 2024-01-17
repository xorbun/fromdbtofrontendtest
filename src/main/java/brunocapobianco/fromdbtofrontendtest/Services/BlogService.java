package brunocapobianco.fromdbtofrontendtest.Services;

import brunocapobianco.fromdbtofrontendtest.Entities.Blog;
import brunocapobianco.fromdbtofrontendtest.Exceptions.NotFoundException;
import brunocapobianco.fromdbtofrontendtest.Repositories.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService
{
    @Autowired
    private BlogDao blogdao;

    public List<Blog> Getblog()
    {
        return blogdao.findAll();
    }
    public Blog findById(UUID id_blog)
    {
        return blogdao.findById(id_blog).orElseThrow(()->new NotFoundException(id_blog));
    }
    public Blog save(Blog body)
    {
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
