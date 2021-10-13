package forum.control;

import forum.model.Comment;
import forum.model.Post;
import forum.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentControl {

    private final PostService postService;

    public CommentControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/comments")
    public String comments(@RequestParam int id, Model model) {
        model.addAttribute("post", postService.findByIdPost(id));
        return "post/post";
    }

    @GetMapping("/comments/add")
    public String add(@RequestParam int id, Model model) {
        model.addAttribute("post", postService.findByIdPost(id));
        model.addAttribute("comment", new Comment());
        return "comment/edit";
    }

    @PostMapping("/comment/save")
    public String save(@RequestParam int id, @ModelAttribute Comment comment) {
        Post post = postService.findByIdPost(id);
        postService.commentAdd(post, comment);
        return "redirect:/comments?id=" + id;
    }
}
