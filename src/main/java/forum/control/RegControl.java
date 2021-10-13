package forum.control;

import forum.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*@Controller*/
public class RegControl {

    private final PasswordEncoder passwordEncoder;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public RegControl(PasswordEncoder passwordEncoder, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.passwordEncoder = passwordEncoder;
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        if (inMemoryUserDetailsManager.userExists(user.getUsername())) {
            return "redirect:/reg?error=true";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        inMemoryUserDetailsManager.createUser(
                org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build());
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