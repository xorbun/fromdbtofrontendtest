package brunocapobianco.fromdbtofrontendtest.Repositories;

import brunocapobianco.fromdbtofrontendtest.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID>
{
    Optional<User> findByEmail(String email);
}
