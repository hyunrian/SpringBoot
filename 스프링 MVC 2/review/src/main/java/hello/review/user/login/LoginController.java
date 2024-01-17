package hello.review.user.login;

import hello.review.LoginConst;
import hello.review.user.domain.User;
import hello.review.user.domain.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final LoginService loginService;

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
//        log.info("로그인 페이지 이동 컨트롤러");
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpSession session) {

        log.info("로그인 처리 컨트롤러 호출 시점에서의 session [{}]", session.toString());

        if (bindingResult.hasErrors()) {
            log.info("로그인 오류 {}", bindingResult.getFieldErrors());
            return "user/login";
        }

        User loginUser = loginService.login(loginForm); // 이 데이터를 세션에 담음
        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다."); // 글로벌 오류 생성
            log.info("입력 오류 [{}]", bindingResult.getGlobalError().getDefaultMessage());
            return "user/login";
        }

        log.info("로그인 성공, 세션 추가, homeController로 이동");
        session.setAttribute(LoginConst.LOGIN_USER, loginUser);
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute(LoginConst.LOGIN_USER) != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    // 시작하기
    @GetMapping("/temp")
    public String temp() {
        return "temp";
    }
}
