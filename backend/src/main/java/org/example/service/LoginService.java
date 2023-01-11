package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.User;
import org.example.model.UserContent;
import org.example.repository.ContentRepository;
import org.example.repository.UserContentRepository;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private static final int COOKIE_AGE_IN_SECONDS = 28800;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final UserContentRepository userContentRepository;

    @Transactional
    public ResponseEntity<?> saveNewUser(HttpServletResponse response) {
        String cookieUuid = UUID.randomUUID().toString();
        User userToSave = new User(cookieUuid);

        addCookieToResponse(response, cookieUuid);
        userRepository.save(userToSave);
        createPipelineForUser(userToSave);
        return new ResponseEntity<>(cookieUuid, HttpStatus.CREATED);
    }

    private void createPipelineForUser(User currentUser) {
        UserContent pipeline = new UserContent(currentUser, contentRepository.findAll());
        userContentRepository.saveAll(pipeline.getListOf());
    }

    private void addCookieToResponse(HttpServletResponse response, String cookieUuid) {
        Cookie newCookie = new Cookie("lets-pick", cookieUuid);
        newCookie.setMaxAge(COOKIE_AGE_IN_SECONDS);
        response.addCookie(newCookie);
    }
}
