package forum.control;

import forum.model.Post;
import forum.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("post", postService.createNewPost());
        return "post/edit";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("post", postService.findByIdPost(id));
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        postService.postSave(post);
        return "redirect:/";
    }

}
