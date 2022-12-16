package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class SimpleContentPublish implements PublisherSelector {
    private final ContentRepository contentRepository;
    private final ContentMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ContentResponse> findContentForView(Long currentUserId, String contentType, List<ContentResponse> watchedContent) {
        List<Content> preparedNotWatchedContent = contentRepository
                .findNotWatchedContentByUserId
                        (currentUserId, contentType);

        return preparedNotWatchedContent.size() == 2 ?
                mapper.toListDto(preparedNotWatchedContent) :
                mapper.toListDto(contentRepository.findRandomContent(currentUserId, contentType));
    }

    @Override
    public PublisherSelectType getStrategyType() {
        return PublisherSelectType.SIMPLE_CONTENT_SELECT;
    }
}
