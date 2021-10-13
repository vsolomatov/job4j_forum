package forum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String desc;

    private Date created;

    private String username;

    public static Comment of(String desc) {
        Comment comment = new Comment();
        comment.desc = desc;
        comment.created = new Date();
        return comment;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }
    public void setUsername(String user) {
        this.username = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id
                && Objects.equals(desc, comment.desc)
                && Objects.equals(created, comment.created)
                && Objects.equals(username, comment.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, created, username);
    }

    @Override
    public String toString() {
        return "Comment{"
                + "id=" + id
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", userName=" + username
                + '}';
    }
}
