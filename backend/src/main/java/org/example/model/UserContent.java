package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContent {
    @Transient
    List<UserContent> listOf = new ArrayList<>();
    @EmbeddedId
    @EqualsAndHashCode.Exclude
    private UserContentId id = new UserContentId();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("contentId")
    private Content content;
    private boolean isWatched = false;

    private int liked = -1;

    public UserContent(User user, Content content) {
        this.user = user;
        this.content = content;
        this.id = new UserContentId(user.getId(), content.getId());
    }

    public UserContent(User user, List<Content> allContent) {
        for (Content content : allContent) {
            this.listOf.add(new UserContent(user, content));
            this.id = new UserContentId(user.getId(), content.getId());
        }
    }

    public void prepareContentForUser(User user, List<Content> contentList) {
        for (Content content : contentList) {
            this.listOf.add(new UserContent(user, content));
        }
    }
}
