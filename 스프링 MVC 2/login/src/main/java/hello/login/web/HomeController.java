package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if (memberId == null) {
            return "home";
        }

        // 로그인된 홈으로
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member)sessionManager.getSession(request);

        // 로그인된 홈으로
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인된 페이지로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        // @SessionAttribute: 세션을 편하게 관리할 수 있음. 세션을 생성하는 기능은 없음

        // 세션에 회원 데이터가 없으면 홈으로
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인된 페이지로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {
        // @Login: ArgumentResolver를 이용하여 직접 애너테이션을 만들고 등록함
        // 공통작업이 필요할 때 컨트롤러를 더욱 편리하게 사용할 수 있음
        // 컨트롤러 호출 직전에 호출되어서 필요한 파라미터 정보를 생성해줌
        // 여기서는 세션에 있는 로그인 회원 정보인 Member 객체를 찾아서 반환해 줌
        // argumentresolver/Login - 애너테이션 생성
        // argumentresolver/LoginMemberArgumentResolver - 애너테이션 설정
        // WebConfig - 애너테이션 등록

        // 세션에 회원 데이터가 없으면 홈으로
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인된 페이지로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}