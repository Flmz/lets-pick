package org.example.mapper;

import org.example.dto.ContentResponse;
import org.example.model.Content;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ContentMapperTest {
    private final ContentMapper mapper = ContentMapper.INSTANCE;
    private final ContentResponse contentResponse = ContentResponse.builder()
            .url(UUID.randomUUID().toString())
            .type(getRandomType()).build();

    @Test
    public void shouldConvertToEntity() {
        Content content = mapper.toEntity(contentResponse);
        assertEquals(content.getUrl(), contentResponse.getUrl());
        assertEquals(content.getType(), contentResponse.getType());
    }

    @Test
    public void idContentShouldBeNull() {
        Content content = mapper.toEntity(contentResponse);
        assertNull(content.getId());
    }

    @Test
    public void shouldConvertToDTO() {
        ContentResponse contentResponse = mapper.toDTO(content1);
        assertEquals(contentResponse.getType(), content1.getType());
        assertEquals(contentResponse.getUrl(), content1.getUrl());
    }

    @Test
    public void shouldConvertListToDTOList() {
        List<Content> contentList = new ArrayList<>() {{
            add(content1);
            add(content2);
        }};

        List<ContentResponse> contentResponseList = mapper.toListDto(contentList);
        assertEquals(contentResponseList.get(0).getUrl(), contentList.get(0).getUrl());
        assertEquals(contentResponseList.get(0).getType(), contentList.get(0).getType());
    }
}
