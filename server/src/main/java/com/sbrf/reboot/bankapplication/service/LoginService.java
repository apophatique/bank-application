package com.sbrf.reboot.bankapplication.service;

import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.exception.LoginFailedException;
import model.SignInRequest;
import model.SignUpRequest;

public interface LoginService {
    User signIn(SignInRequest signInRequest) throws LoginFailedException;
    void signUp(SignUpRequest signUpRequest);
}
