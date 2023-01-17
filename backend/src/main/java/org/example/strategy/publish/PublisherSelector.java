package org.example.strategy.publish;

import org.example.model.Content;
import org.example.model.User;
import org.example.model.enums.GameType;

import java.util.List;


public interface PublisherSelector {

    List<Content> findContentForUser(User currentUser, String contentTypeRequest);

    GameType getStrategyType();
}
