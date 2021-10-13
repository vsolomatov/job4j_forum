package forum.control;

import forum.model.User;
import forum.repository.AuthorityRepository;
import forum.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegJdbcControl {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public RegJdbcControl(PasswordEncoder encoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        if (userRepository.findByName(user.getUsername()).isPresent()) {
            return "redirect:/reg?error=true";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Пользователь с таким именем уже зарегистрирован!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}
