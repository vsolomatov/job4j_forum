package forum.store;

import forum.model.Comment;
import forum.model.Post;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ForumMem implements ForumStore {

    private final HashMap<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger postAtomicInteger = new AtomicInteger(2);
    private final AtomicInteger commentAtomicInteger = new AtomicInteger(1);

    public ForumMem() {
        Post post1 = Post.of("Продаю авто", "Тема о продаже легковых автомобилей");
        Comment comment = Comment.of("А почем продаешь?");
        comment.setId(1);
        comment.setUsername("admin");
        comment.setCreated(new Date());
        post1.commentAdd(comment);
        post1.setId(1);
        posts.put(1, post1);

        Post post2 = Post.of("Продаю квартиру", "Тема о продаже некоммерческого жилья");
        post2.setId(2);
        posts.put(2, post2);
    }

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    @Override
    public void commentAdd(Post post, Comment comment) {
        comment.setId(commentAtomicInteger.addAndGet(1));
        comment.setCreated(new Date());
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUsername(principal.getUsername());
        post.commentAdd(comment);
    }

    @Override
    public void postSave(Post post) {
        if (post.getId() == 0) {
            int next = postAtomicInteger.addAndGet(1);
            post.setId(next);
        }
        if (post.getCreated() == null) {
           post.setCreated(new Date());
        }
        posts.put(post.getId(), post);
    }

}
