package org.example.strategy;

import org.example.model.Content;
import org.example.model.enums.ContentType;
import org.example.util.Utils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoContentPublisher implements ContentPublisher {
    @Override
    public List<Content> sendNewContent(List<Content> userContent) {
        List<Content> contentForUser = Utils.getTwoVideosForUser(userContent, ContentType.VIDEO);

        if (contentForUser.size() < 2) {
            //TODO
        }

        return contentForUser;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.VIDEO;
    }

}