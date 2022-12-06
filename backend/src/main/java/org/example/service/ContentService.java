package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ContentService {
    private final ContentRepository contentRepository;

    public Optional<Content> findByUrl(String contentUrl) {
        return contentRepository.findByUrlIgnoreCase(contentUrl);
    }

    @Transactional
    public Content save(Content contentToSave) {
        return contentRepository.save(contentToSave);
    }

    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }
}
