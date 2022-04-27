package com.sbrf.reboot.bankapplication.controller;

import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для обработки запросов для получения информации о пользователях
 */
@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users
     *
     * @return список пользователей
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Получить пользователя по запросу "/users/{имя пользоватея}"
     *
     * @param userName имя пользователя
     * @return user {@link User}
     */
    @GetMapping(value = "/{username}")
    @ResponseBody
    public ResponseEntity<User> getUserByName(final @PathVariable("username") String userName) {
        return ResponseEntity.ok(userRepository.findByUsername(userName));
    }

}
