package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.mapper.ContentMapper;
import org.example.model.User;
import org.example.model.enums.ContentType;
import org.example.model.enums.GameType;
import org.example.strategy.publish.PublisherFactory;
import org.example.strategy.publish.PublisherSelector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final UserService userService;
    private final PublisherFactory factory;

    @Transactional
    public List<ContentResponse> startApp(String cookies, String type) {
        String contentTypeForSearch = parseTypeParam(type).name();

        User currentUser = userService.findUserByCookie(cookies);

        PublisherSelector selector = getSelector(GameType.RANDOM);

        return ContentMapper.INSTANCE.toListDto(selector.findContentForUser(currentUser, contentTypeForSearch));
    }

    private ContentType parseTypeParam(String type) {
        if (type == null || type.isBlank() && type.isEmpty()) return getRandomContentType();
        try {
            return ContentType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return getRandomContentType();
        }
    }

    private PublisherSelector getSelector(GameType type) {
        return factory.getStrategy(type);
    }

    private ContentType getRandomContentType() {
        int pick = new Random().nextInt(ContentType.values().length);
        return ContentType.values()[pick];
    }
}

