package review.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 10)
    private String userName;
    private String phoneNum;

    public User(String userName, String phoneNum) {
        this.userName = userName;
        this.phoneNum = phoneNum;
    }
}


