package brunocapobianco.fromdbtofrontendtest.Controllers;

import brunocapobianco.fromdbtofrontendtest.Entities.Blog;
import brunocapobianco.fromdbtofrontendtest.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Blogs")
public class BlogControler
{
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getBlogs()
    {
        return blogService.Getblog();
    }
    @GetMapping("/{id_blog}")
    public  Blog getBlogById(@PathVariable UUID id_blog)
    {
        return blogService.findById(id_blog);
    }
    @PostMapping
    public Blog createBlog(@RequestBody Blog newBlogPayload)
    {
        return blogService.save(newBlogPayload);
    }
    @PutMapping("/{id_blog}")
    public Blog findByIdAndUpdate(@PathVariable UUID id_blog,@RequestBody Blog modifyBlogPayload)
    {
        return blogService.findByIdAndUpdate(id_blog,modifyBlogPayload);
    }
    @DeleteMapping("/{id_blog}")
    public void findByIdAndDelete(@PathVariable UUID id_blog)
    {
        blogService.findByIdAndDelete(id_blog);
    }
}
