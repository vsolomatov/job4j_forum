package forum.store;

import forum.model.Comment;
import forum.model.Post;

import java.util.Collection;

public interface ForumStore {

    Collection<Post> findAllPosts();

    Post findByIdPost(int id);

    void commentAdd(Post post, Comment comment);

    void postSave(Post post);

}
