package org.example.repository;

import org.example.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    String VARIABLES = " c.id, c.type, c.url ";

    Optional<Content> findByUrl(String url);

    @Query(nativeQuery = true, value = "select" + VARIABLES +
            """
                    FROM content c
                        JOIN user_content uc
                            ON c.id = uc.content_id
                                WHERE uc.user_id = :id
                                 AND c.type = :type
                                 AND uc.is_watched = false
                                      ORDER BY random() limit :limit
                    """)
    List<Content> findNotWatchedContentByUserId(Long id, String type, int limit);

    @Query(nativeQuery = true, value = "select" + VARIABLES +
            """
                    FROM content c
                        JOIN user_content uc
                            ON c.id = uc.content_id
                                WHERE uc.user_id = :userId
                                 AND c.type = :contentType
                                 AND c.id != :preparedContentId
                                      ORDER BY random()
                    """)
    Optional<Content> findRandomContent(Long userId, String contentType, long preparedContentId);

    @Query(nativeQuery = true, value = "select" + VARIABLES +
            """
                    FROM content c
                        JOIN user_content uc
                            ON c.id = uc.content_id
                                WHERE uc.user_id = :userId
                                 AND c.type = :contentType
                                      ORDER BY random() limit :limit
                    """)
    List<Content> findRandomContent(Long userId, String contentType, int limit);
}
