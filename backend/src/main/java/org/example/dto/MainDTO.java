package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.example.model.enums.ContentType;


@Getter
@Builder
public class MainDTO {
    @JsonProperty("likedContent")
    @NotNull(message = "liked content cannot be null")
    private ContentDTO likedContent;

    @JsonProperty("unlikedContent")
    @NotNull(message = "not liked content cannot be null")
    private ContentDTO unLikedContent;

    @JsonProperty("type")
    private ContentType contentType;
}
