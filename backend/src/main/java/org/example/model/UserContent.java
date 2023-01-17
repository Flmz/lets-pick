package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class UserContent {

    @EmbeddedId
    @EqualsAndHashCode.Exclude
    private UserContentId id = new UserContentId();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @MapsId("contentId")
    private Content content;

    @Builder.Default
    private boolean isWatched = false;

    private boolean isLiked;

    @Transient
    List<UserContent> contentForUser = new ArrayList<>();

    public UserContent(User user, Content content) {
        this.user = user;
        this.content = content;
        this.id = new UserContentId(user.getId(), content.getId());
    }

    public UserContent(User user, List<Content> allContent) {
        for (Content content : allContent) {
            this.contentForUser.add(new UserContent(user, content));
            this.id = new UserContentId(user.getId(), content.getId());
        }
    }
}
