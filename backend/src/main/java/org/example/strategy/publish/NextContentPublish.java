package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NextContentPublish implements PublisherSelector {

    private final ContentRepository contentRepository;
    private final ContentMapper mapper;

    @Override
    @Transactional
    public List<ContentResponse> findContentForView(Long currentUserId, String contentTypeName, List<ContentResponse> watchedContent) {
        String likedContentUrl = watchedContent.get(0).getUrl();
        String notLikedContentUrl = watchedContent.get(1).getUrl();
        contentRepository.setWatched(likedContentUrl, 1, currentUserId);
        contentRepository.setWatched(notLikedContentUrl, 0, currentUserId);

        List<Content> preparedNotWatchedContent = contentRepository
                .findNotWatchedContentByUserId
                        (currentUserId, contentTypeName);

        return preparedNotWatchedContent.size() == 2 ?
                mapper.toListDto(preparedNotWatchedContent) :
                mapper.toListDto(contentRepository.findRandomContent(currentUserId, contentTypeName));
    }

    @Override
    public List<ContentResponse> findContentForView(Long currentUserId, String requestedUserContentType) {
        return null;
    }

    @Override
    public PublisherSelectType getStrategyType() {
        return PublisherSelectType.NEXT_CONTENT_SELECT;
    }
}
