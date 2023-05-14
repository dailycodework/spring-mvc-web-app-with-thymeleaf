package com.dailycodework.sbend2endapplication.registration.password;

import com.dailycodework.sbend2endapplication.utility.TokenExpirationTime;
import com.dailycodework.sbend2endapplication.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Sampson Alfred
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PasswordResetToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = TokenExpirationTime.getExpirationTime();
    }
}
