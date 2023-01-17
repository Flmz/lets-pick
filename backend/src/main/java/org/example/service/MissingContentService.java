package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.ContentNotExistException;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MissingContentService {
    private final ContentRepository contentRepository;

    public List<Content> findMissingContent(List<Content> preparedContent, long userId, String contentType) {
        switch (preparedContent.size()) {
            case 1 -> preparedContent.add(
                    contentRepository.findRandomContent(
                            userId, contentType
                            , preparedContent.get(0).getId()).orElseThrow(ContentNotExistException::new)
            );
            case 0 -> preparedContent.addAll(
                    contentRepository.findRandomContent(
                            userId, contentType
                            , 2));
        }
        return preparedContent;
    }

}
