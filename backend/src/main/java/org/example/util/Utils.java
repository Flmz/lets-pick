package org.example.util;

import org.example.exception.CannotParseVideoIdException;
import org.springframework.validation.BindingResult;

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
}
