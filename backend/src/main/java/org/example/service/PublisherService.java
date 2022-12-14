package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.dto.ContentDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.Content;
import org.example.model.User;
import org.example.model.enums.ContentType;
import org.example.repository.ContentRepository;
import org.example.repository.UserRepository;
import org.example.strategy.publish.PublisherFactory;
import org.example.strategy.publish.PublisherSelector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.example.util.Utils.getCookieUUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PublisherService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    PublisherFactory factory;

    public List<ContentDTO> startApp(HttpServletRequest request) {
        User currentUser = userRepository.findByCookie(getCookieUUID(request))
                .orElseThrow(UserNotFoundException::new);
        Long userId = currentUser.getId();

        List<Content> prepareNewContentForUser = contentRepository.findNotWatchedContentByUserId(
                userId, getRandomContentType().name());
        int sizeOfPreparedContent = prepareNewContentForUser.size();

        if (sizeOfPreparedContent == 0) {
            return null; //TODO
        }

        return getSelector(sizeOfPreparedContent).findContentForView(
                prepareNewContentForUser, userId);
    }

    private PublisherSelector getSelector(int size) {
        return factory.getStrategy(size);
    }

    private ContentType getRandomContentType() {
        int pick = new Random().nextInt(ContentType.values().length);
        return ContentType.values()[pick];
    }
}

