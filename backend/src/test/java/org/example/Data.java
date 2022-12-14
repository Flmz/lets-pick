package org.example;

import org.example.model.Content;
import org.example.model.User;
import org.example.model.enums.ContentType;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Data {
    public static User user1;
    public static User user2;
    public static User user3;
    public static User user4;
    public static User user5;
    public static User user6;
    public static User user7;
    public static User user8;

    public static Content content1;
    public static Content content2;
    public static Content content3;
    public static Content content4;
    public static Content content5;
    public static Content content6;
    public static Content content7;
    public static Content content8;
    public static Content content9;
    public static Content content10;
    public static Content content11;
    public static Content content12;
    public static Content content13;
    public static Content CONTENT_VIDEO_URL;

    public static List<Content> contentList;


    static {
        user1 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user2 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user3 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user4 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user5 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user6 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user7 = User.builder().cookie(UUID.randomUUID().toString()).build();
        user8 = User.builder().cookie(UUID.randomUUID().toString()).build();

        content1 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content2 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content3 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content4 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content5 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content6 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content7 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content8 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content9 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content10 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        content11 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content12 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.VIDEO).build();
        content13 = Content.builder().url("http://" + UUID.randomUUID()).type(ContentType.IMAGE).build();
        CONTENT_VIDEO_URL = Content.builder().url("https://www.youtube.com/watch?v=uV8j4_Xcg-s").type(ContentType.VIDEO).build();

        contentList = List.of(content1, content2, content3,
                content4, content5, content6, content7, content8, content9, content10
                , content11, content12, content13);
    }

    public static ContentType getRandomType() {
        int pick = new Random().nextInt(ContentType.values().length);
        return ContentType.values()[pick];
    }
}
