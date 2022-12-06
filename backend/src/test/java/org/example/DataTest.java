package org.example;

import org.example.dto.ContentDTO;
import org.example.model.Content;
import org.example.model.enums.Category;
import org.example.model.enums.ContentType;

import java.util.ArrayList;

public class DataTest {
    public static final ContentDTO contentDTO = new ContentDTO();
    public static final Content content = new Content();

    static {
        contentDTO.setType(ContentType.VIDEO);
        contentDTO.setUrl("Z9dvZyEofAg");
        contentDTO.setCategories(new ArrayList<>() {{
            add(Category.STAND_UP);
            add(Category.MEM);
        }});

        content.setId(1L);
        content.setType(ContentType.IMAGE);

        content.addCategory(Category.SPORT);
        content.addCategory(Category.CHILDREN);
        content.setUrl("https://static01.nyt.com/images/2020/12/14/well/14google-photo/14google-photo-videoSixteenByNineJumbo1600.jpg");
    }
}
