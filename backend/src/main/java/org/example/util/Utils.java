package org.example.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.exception.CannotParseVideoIdException;
import org.example.exception.UserNotFoundException;
import org.example.model.Content;
import org.example.model.enums.ContentType;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    public static String getVideoId(String youTubeUrl) {
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(youTubeUrl);
        if (matcher.find()) {
            return matcher.group(1);

        }
        throw new CannotParseVideoIdException(youTubeUrl + "failed");
    }

    public static String writeErrorMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + "-" + fieldError.getDefaultMessage() + ".\n")
                .collect(Collectors.joining());
    }

    public static String getCookieUUID(HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new UserNotFoundException("No cookies in db");
        }
        return request.getCookies()[0].getName();
    }

    public static List<Content> getTwoVideosForUser(List<Content> userContent, ContentType type){
        List<Content> contentForUser = new ArrayList<>(2);
        int counter = 0;
        for (Content content : userContent) {
            if (counter == 2) {
                break;
            }
            if (content.getType() == type) {
                counter++;
                contentForUser.add(content);
            }
        }
        return contentForUser;
    }
}
