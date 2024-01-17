package brunocapobianco.fromdbtofrontendtest.Repositories;

import brunocapobianco.fromdbtofrontendtest.Entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
@Repository
public interface BlogDao extends JpaRepository<Blog, UUID> {
}
