package forum.store;

import forum.model.Comment;
import forum.model.Post;
import forum.repository.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public class ForumSpring implements ForumStore {

    private final PostRepository postRepository;

    public ForumSpring(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return (Collection<Post>) postRepository.findAll();
    }

    @Override
    public Post findByIdPost(int id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void commentAdd(Post post, Comment comment) {
        comment.setCreated(new Date());
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUsername(principal.getUsername());
        post.commentAdd(comment);
    }

    @Override
    public void postSave(Post post) {
        postRepository.save(post);
    }
}
