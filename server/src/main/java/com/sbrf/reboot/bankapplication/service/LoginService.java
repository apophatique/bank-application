package com.sbrf.reboot.bankapplication.service;

import com.sbrf.reboot.bankapplication.entities.Balance;
import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.exception.LoginFailedException;
import com.sbrf.reboot.bankapplication.exception.UserAlreadyExistsException;
import com.sbrf.reboot.bankapplication.repositories.BalanceRepository;
import com.sbrf.reboot.bankapplication.repositories.UserRepository;
import model.SignInRequest;
import model.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String SIGN_IN_EXCEPTION_MESSAGE = "Either login or password were incorrect.";

    public LoginService(final UserRepository userRepository, final BalanceRepository balanceRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.balanceRepository = balanceRepository;
    }

    public User signIn(final SignInRequest signInRequest) throws LoginFailedException {
        final User user = userRepository.findByUsername(signInRequest.getUsername());

        if (user == null) {
            throw new LoginFailedException(SIGN_IN_EXCEPTION_MESSAGE);
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new LoginFailedException(SIGN_IN_EXCEPTION_MESSAGE);
        }
        return user;
    }

    /**
     *
     * @param signUpRequest {@link SignUpRequest}
     */
    public void signUp(final SignUpRequest signUpRequest) {
        final String username = signUpRequest.getUsername();
        final String password = signUpRequest.getPassword();
        final String encodedPassword = passwordEncoder.encode(password);

        if (userRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistsException(String.format("User with name %s already exists", username));
        }

        User user = userRepository.save(new User(
                signUpRequest.getUsername(),
                encodedPassword
        ));
        System.out.println(user);
        Balance balance = new Balance();
        balance.setUser(user);
        balanceRepository.save(balance);
    }
}
