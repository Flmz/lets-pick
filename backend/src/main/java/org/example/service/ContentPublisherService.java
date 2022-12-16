package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.exception.NoSuchContentTypeException;
import org.example.model.User;
import org.example.model.enums.ContentType;
import org.example.strategy.publish.PublisherFactory;
import org.example.strategy.publish.PublisherSelectType;
import org.example.strategy.publish.PublisherSelector;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static org.example.strategy.publish.PublisherSelectType.NEXT_CONTENT_SELECT;
import static org.example.strategy.publish.PublisherSelectType.SIMPLE_CONTENT_SELECT;

@Service
@AllArgsConstructor
public class ContentPublisherService {

    private final UserService userService;
    private final PublisherFactory factory;

    public List<ContentResponse> startApp(java.lang.String cookies, java.lang.String type, List<ContentResponse> watchedContent) {
        String contentTypeName = parseTypeParam(type).name();
        User currentUser = userService.findUserByCookie(cookies);
        long currentUserId = currentUser.getId();

        if (watchedContent == null) {
            PublisherSelector selector = getSelector(SIMPLE_CONTENT_SELECT);
            return selector.findContentForView(currentUserId, contentTypeName, null);
        }

        PublisherSelector selector = getSelector(NEXT_CONTENT_SELECT);
        return selector.findContentForView(currentUserId, contentTypeName, watchedContent);

    }

    private ContentType parseTypeParam(java.lang.String type) {
        if (type == null || type.isBlank() && type.isEmpty()) {
            return getRandomContentType();
        } else {
            try {
                return ContentType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException exception) {
                throw new NoSuchContentTypeException();
            }
        }
    }

    private PublisherSelector getSelector(PublisherSelectType type) {
        return factory.getStrategy(type);
    }

    private ContentType getRandomContentType() {
        int pick = new Random().nextInt(ContentType.values().length);
        return ContentType.values()[pick];
    }
}

