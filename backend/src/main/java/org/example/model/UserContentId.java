package org.example.model;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class UserContentId implements Serializable {
    private Long userId;

    private Long contentId;

    public UserContentId(Long userId, Long contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContentId that = (UserContentId) o;
        return Objects.equals(getUserId(), that.userId) && Objects.equals(getContentId(), that.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentId);
    }
}
