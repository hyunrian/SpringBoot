package hello.review;

import hello.review.user.domain.User;
import hello.review.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);

//		UserRepository userRepository = new UserRepository();
//
//		User user = new User();
//		user.setUserid("test");
//		user.setPassword("1");
//		userRepository.join(user);

	}

}
