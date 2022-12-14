package org.example.mapper;

import org.example.dto.ContentDTO;
import org.example.model.Content;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ContentMapperTest {
    private final ContentMapper mapper = ContentMapper.CONTENT_MAPPER;
    private final ContentDTO contentDTO = ContentDTO.builder()
            .url(UUID.randomUUID().toString())
            .type(getRandomType()).build();

    @Test
    public void shouldConvertToEntity() {
        Content content = mapper.toEntity(contentDTO);
        assertEquals(content.getUrl(), contentDTO.getUrl());
        assertEquals(content.getType(), contentDTO.getType());
    }

    @Test
    public void idContentShouldBeNull() {
        Content content = mapper.toEntity(contentDTO);
        assertNull(content.getId());
    }

    @Test
    public void shouldConvertToDTO() {
        ContentDTO contentDTO = mapper.toDTO(content1);
        assertEquals(contentDTO.getType(), content1.getType());
        assertEquals(contentDTO.getUrl(), content1.getUrl());
    }

    @Test
    public void shouldConvertListToDTOList() {
        List<Content> contentList = new ArrayList<>() {{
            add(content1);
            add(content2);
        }};

        List<ContentDTO> contentDtoList = mapper.toListDto(contentList);
        assertEquals(contentDtoList.get(0).getUrl(), contentList.get(0).getUrl());
        assertEquals(contentDtoList.get(0).getType(), contentList.get(0).getType());
    }
}
