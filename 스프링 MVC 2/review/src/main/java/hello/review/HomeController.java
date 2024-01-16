package hello.review;

import hello.review.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        log.info("homeController 실행");
        User user = (User) session.getAttribute("user");
        log.info("session user={}", user);
        model.addAttribute("user", user);
        return "home";
    }
}
