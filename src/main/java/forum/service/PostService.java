package forum.service;

import forum.model.Comment;
import forum.model.Post;
import forum.store.ForumStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostService {

    @Autowired
    @Qualifier("forumSpring")
    private ForumStore forumStore;

    public Collection<Post> findAllPosts() {
        return forumStore.findAllPosts();
    }

    public Post findByIdPost(int id) {
        return forumStore.findByIdPost(id);
    }

    public Post createNewPost() {
        return Post.of("", "");
    }

    public void postSave(Post post) {
        forumStore.postSave(post);
    }

    public void commentAdd(Post post, Comment comment) {
        forumStore.commentAdd(post, comment);
        forumStore.postSave(post);
    }

}