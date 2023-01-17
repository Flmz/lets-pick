package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import org.example.model.Content;
import org.example.model.User;
import org.example.model.enums.GameType;
import org.example.repository.ContentRepository;
import org.example.service.MissingContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RandomContentSelector implements PublisherSelector {
    private static final int AMOUNT_CONTENT = 2;
    private final ContentRepository contentRepository;
    private final MissingContentService missingService;

    @Override
    public List<Content> findContentForUser(User currentUser, String contentTypeRequest) {
        List<Content> preparedContent = contentRepository
                .findNotWatchedContentByUserId(currentUser.getId(), contentTypeRequest, AMOUNT_CONTENT);
        if (preparedContent.size() == 2) {
            return preparedContent;
        }

        return missingService.findMissingContent(preparedContent, currentUser.getId(), contentTypeRequest);
    }

    @Override
    public GameType getStrategyType() {
        return GameType.RANDOM;
    }
}
