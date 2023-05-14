package com.dailycodework.sbend2endapplication.registration.password;

import com.dailycodework.sbend2endapplication.user.User;
import com.dailycodework.sbend2endapplication.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

/**
 * @author Sampson Alfred
 */
@Service
@RequiredArgsConstructor
public class PasswordResetTokenService implements IPasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        var passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
    @Override
    public String validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> theToken = passwordResetTokenRepository.findByToken(token);
        if (theToken.isEmpty()){
            return "INVALID";
        }
        Calendar calendar = Calendar.getInstance();
        if ((theToken.get().getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "EXPIRED";
        }
        return "VALID";
    }

    @Override
    public Optional<User> findUserByToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).get().getUser());
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
