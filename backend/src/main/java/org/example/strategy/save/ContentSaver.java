package org.example.strategy.save;

import org.example.model.Content;
import org.example.model.enums.ContentType;

public interface ContentSaver {
    Content save(Content contentToSave);

    ContentType getContentType();
}

