package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.example.model.enums.ContentType;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class ContentResponse {
    @JsonProperty("url")
    @NotNull
    String url;

    @JsonProperty("type")
    ContentType type;
}
