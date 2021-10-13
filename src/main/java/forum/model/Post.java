package forum.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "description")
    private String desc;

    private Date created;

    @OneToMany(cascade = {CascadeType.ALL})
    private final Set<Comment> comments = new HashSet<>();

    public static Post of(String name, String desc) {
        Post post = new Post();
        post.name = name;
        post.desc = desc;
        post.created = new Date();
        return post;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void commentAdd(Comment comment) {
        comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(desc, post.desc)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", comments=" + comments
                + '}';
    }
}