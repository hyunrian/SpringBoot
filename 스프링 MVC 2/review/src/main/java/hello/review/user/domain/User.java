package hello.review.user.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class User {

    private long id;

    @NotNull
    private String userid;

    @NotEmpty
    private String password;
//    private UserImage userImage;

}
