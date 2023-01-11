package org.example.strategy.save;

import lombok.AllArgsConstructor;
import org.example.exception.CannotParseVideoIdException;
import org.example.model.Content;
import org.example.model.enums.ContentType;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class VideoContentSaver implements ContentSaver {

    private final ContentRepository contentRepository;

    private static String getVideoId(String youTubeUrl) {
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/" +
                "(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))" +
                "([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(youTubeUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new CannotParseVideoIdException(youTubeUrl + "failed");
    }

    @Override
    public Content save(Content contentToSave) {
        contentToSave.setUrl(getVideoId(contentToSave.getUrl()));
        return contentRepository.save(contentToSave);
    }

    @Override
    public ContentType getContentType() {
        return ContentType.VIDEO;
    }
}