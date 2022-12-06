package org.example.mapper;

import org.example.dto.ContentDTO;
import org.example.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    ContentMapper CONTENT_MAPPER = Mappers.getMapper(ContentMapper.class);

    @Mapping(target = "id", ignore = true)
    Content toEntity(ContentDTO contentDTO);

    ContentDTO toDTO(Content content);

    default List<ContentDTO> toListDto(List<Content> contentList) {
        return new ArrayList<>((contentList
                .stream()
                .map(this::toDTO)
                .toList()));
    }
}
