package hello.review.user.login;

import hello.review.user.domain.User;
import hello.review.user.domain.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class LoginController {

    private final UserRepository userRepository;

    // 회원가입
    @GetMapping("/join")
    public String joinView(Model model) {
        model.addAttribute(new User());
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute User user, HttpSession session) {
        log.info("user={}", user);
        userRepository.join(user);
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute(new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
