package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.example.model.Content;
import org.example.model.enums.Category;
import org.example.model.enums.ContentType;

import java.util.List;

@Getter
@Builder
public class MainDTO {
    @JsonProperty("likedContent")
    @NotNull(message = "liked content cannot be null")
    private Content likedContent;

    @JsonProperty("unlikedContent")
    @NotNull(message = "not liked content cannot be null")
    private Content unLikedContent;

    @JsonProperty("categories")
    private List<Category> contentCategories;

    @JsonProperty("type")
    private ContentType contentType;
}
