package forum.repository;

import forum.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT distinct a FROM Post a LEFT JOIN FETCH a.comments ORDER BY a.id")
    Iterable<Post> findAll();
}
