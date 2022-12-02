package org.example.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private static final int COOKIE_AGE_IN_SECONDS = 28800;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> saveNewUser(HttpServletResponse response) {
        String cookieUuid = UUID.randomUUID().toString();
        User userToSave = User.builder().cookie(cookieUuid).build();
        addCookieToResponse(response, cookieUuid);
        userRepository.save(userToSave);
        return ResponseEntity.ok(response);
    }

    private void addCookieToResponse(HttpServletResponse response, String cookieUuid) {
        Cookie newCookie = new Cookie(cookieUuid, "");
        newCookie.setMaxAge(COOKIE_AGE_IN_SECONDS);
        response.addCookie(newCookie);
    }
}
