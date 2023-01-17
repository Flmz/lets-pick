package org.example.strategy.publish;

import org.example.model.enums.GameType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PublisherFactory {
    private final Map<GameType, PublisherSelector> strategies;

    @Autowired
    public PublisherFactory(Set<PublisherSelector> strategies) {
        this.strategies = strategies.stream().collect(Collectors.toMap(
                PublisherSelector::getStrategyType,
                selector -> selector
        ));
    }

    public PublisherSelector getStrategy(GameType type) {
        return strategies.get(type);
    }
}
