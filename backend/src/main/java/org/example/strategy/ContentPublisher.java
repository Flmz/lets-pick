package org.example.strategy;

import org.example.model.Content;
import org.example.model.enums.ContentType;

import java.util.List;

public interface ContentPublisher {
    List<Content> sendNewContent(List<Content> userContent);

    ContentType getContentType();
}
