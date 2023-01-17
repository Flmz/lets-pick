package org.example.mapper;

import org.example.dto.ContentResponse;
import org.example.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mapping(target = "id", ignore = true)
    Content toEntity(ContentResponse contentResponse);

    ContentResponse toDTO(Content content);

    default List<ContentResponse> toListDto(List<Content> contentList) {
        return new ArrayList<>(contentList
                .stream()
                .map(this::toDTO)
                .toList());
    }
}
