package org.example.mapper;

import org.example.dto.ContentDTO;
import org.example.model.Content;
import org.example.model.enums.Category;
import org.example.model.enums.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.DataTest.content;
import static org.example.DataTest.contentDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ContentMapperTest {
    private final ContentMapper mapper = ContentMapper.CONTENT_MAPPER;

    @Test
    public void shouldConvertToEntity() {
        Content content = mapper.toEntity(contentDTO);
        assertEquals(content.getUrl(), contentDTO.getUrl());
        assertEquals(content.getType(), contentDTO.getType());
        assertEquals(content.getCategories().get(0), content.getCategories().get(0));
    }

    @Test
    public void idContentShouldBeNull() {
        Content content = mapper.toEntity(contentDTO);
        assertNull(content.getId());
    }

    @Test
    public void shouldConvertToDTO() {
        ContentDTO contentDTO = mapper.toDTO(content);
        assertEquals(contentDTO.getCategories().get(0), content.getCategories().get(0));
        assertEquals(contentDTO.getType(), content.getType());
        assertEquals(contentDTO.getUrl(), content.getUrl());
    }

    @Test
    public void shouldConvertListToDTOList() {
        List<Content> contentList = new ArrayList<>() {{
            add(content);
            add(Content.builder()
                    .categories(new ArrayList<>() {{
                        add(Category.SPORT);
                        add(Category.MEM);
                    }})
                    .id(2L)
                    .url("someurl")
                    .type(ContentType.IMAGE)
                    .build());
        }};

        List<ContentDTO> contentDtoList = mapper.toListDto(contentList);
        assertEquals(contentDtoList.get(0).getUrl(), contentList.get(0).getUrl());
        assertEquals(contentDtoList.get(0).getType(), contentList.get(0).getType());
        assertEquals(contentDtoList
                        .get(0).getCategories().get(0)
                , contentList.get(0).getCategories().get(0));
    }
}
