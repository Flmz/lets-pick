package org.example.strategy;

import org.example.model.enums.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PublisherFactory {
    private final Map<ContentType, ContentPublisher> strategies;

    @Autowired
    public PublisherFactory(Set<ContentPublisher> strategySet) {
        this.strategies = strategySet.stream().collect(Collectors.toMap(
           ContentPublisher::getContentType,
           selector -> selector
        ));
    }

    public ContentPublisher findStrategy(ContentType contentType) {
        return strategies.get(contentType);
    }

}
