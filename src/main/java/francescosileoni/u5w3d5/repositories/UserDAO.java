package francescosileoni.u5w3d5.repositories;

import francescosileoni.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
