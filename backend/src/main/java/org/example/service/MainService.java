package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.MainDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.model.enums.ContentType;
import org.example.repository.UserRepository;
import org.example.strategy.ContentPublisher;
import org.example.strategy.PublisherFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MainService {
    private final UserRepository userRepository;
    private final PublisherFactory factory;

    @Transactional
    public void startApp(String cookieName, MainDTO mainDTO) {
        User currentUser = userRepository
                .findByCookie(cookieName)
                .orElseThrow(UserNotFoundException::new);

        if (mainDTO.getLikedContent() == null || mainDTO.getUnLikedContent() == null) {
            //return TODO
        }

        produceFollowingContent(currentUser, mainDTO); //TODO return
    }

    public void produceFollowingContent(User currentUser, MainDTO main) {
        ContentType currentType = main.getContentType();
        ContentPublisher selector = getSelector(currentType);
        selector.sendNewContent(currentUser.getNotWatchedContent()); //TODO return
        deleteAndAddContentToUser(currentUser, main);
    }

    private void deleteAndAddContentToUser(User currentUser, MainDTO main) {
        currentUser.addLikedContent(main.getLikedContent());
        currentUser.addNotLikedContent(main.getUnLikedContent());
        currentUser.deleteWatchedContent(main.getLikedContent(), main.getUnLikedContent());
    }

    private ContentPublisher getSelector(ContentType contentType) {
        return factory.findStrategy(contentType);
    }
}
