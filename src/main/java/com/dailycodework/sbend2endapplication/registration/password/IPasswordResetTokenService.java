package com.dailycodework.sbend2endapplication.registration.password;

import com.dailycodework.sbend2endapplication.user.User;

import java.util.Optional;

/**
 * @author Sampson Alfred
 */

public interface IPasswordResetTokenService {
    void createPasswordResetTokenForUser(User user, String token);
    String validatePasswordResetToken(String token);

    Optional<User> findUserByToken(String token);

    void resetPassword(User user, String newPassword);

}
