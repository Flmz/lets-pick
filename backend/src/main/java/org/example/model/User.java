package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "usr")
public class User {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Content> notWatchedContent = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Content> likedContent = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Content> unLikedContent = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String cookie;

    public void addLikedContent(Content content) {
        likedContent.add(content);
    }

    public void addNotLikedContent(Content content) {
        unLikedContent.add(content);
    }

    public void deleteWatchedContent(Content... contentToDelete) {
        for (Content content : contentToDelete) {
            this.notWatchedContent.remove(content);
        }
    }
}
