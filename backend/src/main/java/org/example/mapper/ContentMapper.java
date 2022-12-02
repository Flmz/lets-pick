package org.example.mapper;

import org.example.dto.ContentDTO;
import org.example.model.Content;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    Content toEntity(ContentDTO contentDTO);

    ContentDTO toDTO(Content content);
}
