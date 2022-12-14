package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import org.example.dto.ContentDTO;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PublisherSelectorNeedName implements PublisherSelector {

    private final ContentMapper mapper;

    @Override
    public List<ContentDTO> findContentForView(List<Content> notWatchedCurrentUserContent, Long currentUserId) {
        return mapper.toListDto(notWatchedCurrentUserContent);
    }

    @Override
    public PublisherSelectType getStrategyType() {
        return PublisherSelectType.ALL;
    }
}
