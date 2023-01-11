package org.example.strategy.publish;

import org.example.dto.ContentResponse;

import java.util.List;

public interface PublisherSelector {
    List<ContentResponse> findContentForView(Long currentUserId, String requestedUserContentType
            , List<ContentResponse> watchedContent);

    List<ContentResponse> findContentForView(Long currentUserId, String requestedUserContentType);

    PublisherSelectType getStrategyType();
}
