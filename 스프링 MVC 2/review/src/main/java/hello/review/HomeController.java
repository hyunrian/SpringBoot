package hello.review;

import hello.review.user.domain.User;
import hello.review.user.domain.UserRepository;
import hello.review.user.login.LoginForm;
import hello.review.user.login.annotation.Login;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/")
//    public String home(HttpSession session, Model model) {
//
//        User user = new User();
//        user.setUserid("test");
//        user.setPassword("1");
//        userRepository.join(user);
//
//        if (session.getAttribute(LoginConst.LOGIN_USER) != null) {
//            LoginForm loginUser = (LoginForm) session.getAttribute(LoginConst.LOGIN_USER);
//            log.info("session user={}", loginUser);
////            model.addAttribute("user", loginUser);
//            return "loginhome";
//        }
//
//        return "home";
//    }

//    @GetMapping("/")
//    public String home(@SessionAttribute(name = LoginConst.LOGIN_USER, required = false) LoginForm loginUser, Model model) {
//
//        User user = new User();
//        user.setUserid("test");
//        user.setPassword("1");
//        userRepository.join(user);
//
//        if (loginUser != null) {
//            log.info("session user={}", loginUser);
//            model.addAttribute("user", loginUser); // 세션값을 따로 모델에 담아야 html에서 이용 가능
//            return "loginhome";
//        }
//
//        return "home";
//    }

    @GetMapping("/")
    public String home(@Login User user, Model model) {
//
//        User user = new User();
//        user.setUserid("test");
//        user.setPassword("1");
//        userRepository.join(user);

//        if (loginUser != null) {
//            log.info("session user={}", loginUser);
//            model.addAttribute("user", loginUser); // 세션값을 따로 모델에 담아야 html에서 이용 가능
//            return "loginhome";
//        }
//
//        return "home";

        log.info("homeController 시작");
        log.info("loginUser={}", user);
        //세션에 회원 데이터가 없으면 home
        if (user == null) {
            log.info("사용자 데이터 없음 -> home으로 이동");
            return "home";
        }
        //세션이 유지되면 로그인으로 이동
//        log.info("session user={}", loginUser);
        log.info("loginHome으로 이동");
        model.addAttribute("user", user);
        return "loginHome";
    }


}
