package org.example.strategy.publish;

import org.example.dto.ContentDTO;
import org.example.model.Content;

import java.util.List;

public interface PublisherSelector {

    List<ContentDTO> findContentForView(List<Content> notWatchedCurrentUserContent, Long currentUserId);

    PublisherSelectType getStrategyType();
}
