package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.model.enums.Category;
import org.example.model.enums.ContentType;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContentDTO {
    @JsonProperty("url")
    @NotNull
    private String url;

    @JsonProperty("type")
    private ContentType type;

    @JsonProperty("categories")
    private List<Category> categories = new ArrayList<>();

}
