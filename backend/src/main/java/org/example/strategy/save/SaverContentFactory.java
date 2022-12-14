package org.example.strategy.save;


import org.example.model.enums.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SaverContentFactory {
    private final Map<ContentType, ContentSaver> strategies;

    @Autowired
    public SaverContentFactory(Set<ContentSaver> strategySet) {
        this.strategies = strategySet.stream().collect(Collectors.toMap(
                ContentSaver::getContentType,
                saver -> saver
        ));
    }

    public ContentSaver findStrategy(ContentType contentType) {
        return strategies.get(contentType);
    }

}
