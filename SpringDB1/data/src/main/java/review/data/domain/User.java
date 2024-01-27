package review.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String phoneNum;

    public User(String userName, String phoneNum) {
        this.userName = userName;
        this.phoneNum = phoneNum;
    }
}


