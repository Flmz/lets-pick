package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NextContent {
    @JsonProperty("liked")
    private ContentResponse likedContent;

    @JsonProperty("notLiked")
    private ContentResponse notLikedContent;
}
