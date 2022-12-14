package org.example.strategy.save;

import lombok.AllArgsConstructor;
import org.example.model.Content;
import org.example.model.enums.ContentType;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImageContentSaver implements ContentSaver {
    private final ContentRepository contentRepository;

    @Override
    public Content save(Content contentToSave) {
        return contentRepository.save(contentToSave);
    }

    @Override
    public ContentType getContentType() {
        return ContentType.IMAGE;
    }
}
