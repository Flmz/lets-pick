package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.ContentType;


@NoArgsConstructor
@Getter
@Setter
public class ContentDTO {
    @JsonProperty("url")
    private String url;

    @JsonProperty("type")
    private ContentType type;

}
