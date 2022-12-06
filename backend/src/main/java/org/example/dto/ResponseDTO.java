package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    private final String message;

    private final int status;

    private final MediaType mediaType;
}
