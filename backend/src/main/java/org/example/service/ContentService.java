package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Content;
import org.example.model.enums.ContentType;
import org.example.repository.ContentRepository;
import org.example.strategy.save.ContentSaver;
import org.example.strategy.save.SaverContentFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ContentService {
    private final SaverContentFactory factory;
    private final ContentRepository contentRepository;

    @Transactional
    public Content save(Content contentToSave) {
        return getSaver(contentToSave.getType()).save(contentToSave);
    }

    public Optional<Content> findByUrl(java.lang.String contentUrl) {
        return contentRepository.findByUrl(contentUrl);
    }

    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    private ContentSaver getSaver(ContentType contentType) {
        return factory.findStrategy(contentType);
    }
}
