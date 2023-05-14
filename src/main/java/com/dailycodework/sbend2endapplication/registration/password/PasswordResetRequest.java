package com.dailycodework.sbend2endapplication.registration.password;

import lombok.Data;

/**
 * @author Sampson Alfred
 */
@Data
public class PasswordResetRequest {
    private String email;
    private String newPassword;
    private String confirmPassword;
}
