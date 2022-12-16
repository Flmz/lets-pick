package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.enums.ContentType;

@Getter
@NoArgsConstructor
public class SimpleContent {
    @JsonProperty("url")
    private java.lang.String url;

    @JsonProperty("type")
    private ContentType type;
}
