package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User findUserByCookie(String cookieValue) {
        return userRepository.findByCookie(cookieValue)
                .orElseThrow(UserNotFoundException::new);
    }
}
