package forum.repository;

import forum.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("from User u where u.username=?1")
    Optional<User> findByName(String name);
}