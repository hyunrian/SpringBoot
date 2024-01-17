package hello.review.user.login;

import hello.review.user.domain.User;
import hello.review.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    /**
     * return null이면 로그인 실패
     */
    public User login(LoginForm loginForm) {
        return userRepository.findByLoginId(loginForm.getUserid())
                .filter(u -> u.getPassword().equals(loginForm.getPassword()))
                .orElse(null);
    }
}
