package org.example.service;

import lombok.AllArgsConstructor;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
    private final ContentMapper mapper;
    private final ContentRepository contentRepository;

    public Optional<Content> findByUrl(String contentUrl) {
        return contentRepository.findByUrlIgnoreCase(contentUrl);
    }

    @Transactional
    public ResponseEntity<?> save(Content content) {
        enrichContent(content);
        return ResponseEntity.ok(mapper.toDTO(contentRepository.save(content)));
    }

    private void enrichContent(Content contentToSave) {
        //TODO
    }
}
