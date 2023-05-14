package com.dailycodework.sbend2endapplication.user;

import com.dailycodework.sbend2endapplication.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author Sampson Alfred
 */

public interface IUserService {
    List<User> getAllUsers();
    User registerUser(RegistrationRequest registrationRequest);
   User findByEmail(String email);
}
