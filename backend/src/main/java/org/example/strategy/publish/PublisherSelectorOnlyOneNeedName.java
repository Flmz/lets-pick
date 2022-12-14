package org.example.strategy.publish;

import lombok.AllArgsConstructor;
import org.example.dto.ContentDTO;
import org.example.exception.ContentNotExistException;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.repository.ContentRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class PublisherSelectorOnlyOneNeedName implements PublisherSelector {
    private final ContentRepository contentRepository;
    private final ContentMapper mapper;

    @Override
    public List<ContentDTO> findContentForView(List<Content> preparedContentForUser, Long currentUserId) {
        String type = preparedContentForUser.get(0).getType().name();
        try {
            Content content = contentRepository.findSecondContent(currentUserId, type)
                    .orElseThrow(ContentNotExistException::new);
            preparedContentForUser.add(content);
            return mapper.toListDto(preparedContentForUser);
        } catch (ContentNotExistException exc) {
//            Content content = contentRepository
//                    .findAnotherContent(currentUserId, type);//TODO NEED LOGIC
//            preparedContentForUser.add(content);
            return mapper.toListDto(preparedContentForUser);
        }
    }

    @Override
    public PublisherSelectType getStrategyType() {
        return PublisherSelectType.ONLY_ONE_CONTENT_EXIST;
    }
}
