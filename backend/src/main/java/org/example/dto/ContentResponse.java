package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.example.model.enums.ContentType;

@Value
@Builder
public class ContentResponse {
    @JsonProperty("url")
    @NotNull
    java.lang.String url;

    @JsonProperty("type")
    ContentType type;

}
