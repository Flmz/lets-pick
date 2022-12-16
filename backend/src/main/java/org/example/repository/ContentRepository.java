package org.example.repository;

import org.example.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    String VARIABLES = " c.id, c.type, c.url ";

    Optional<Content> findByUrl(String url);

    @Query(nativeQuery = true, value = "select" + VARIABLES + """
            FROM content c
                JOIN user_content uc
                    ON c.id = uc.content_id
                        WHERE uc.user_id = :id
                         AND c.type = :type
                         AND uc.is_watched = false
                              ORDER BY random() limit 2
            """)
    List<Content> findNotWatchedContentByUserId(@Param("id") Long id, @Param("type") String type);

    @Query(nativeQuery = true, value = "select" + VARIABLES + """
                        FROM content c
                            JOIN user_content uc
                             ON c.id = uc.content_id
                              WHERE uc.user_id = :id
                                 AND c.type = :type ORDER BY random() limit 2
            """)
    List<Content> findRandomContent(@Param("id") Long id, @Param("type") String contentTypeName);

    @Query(nativeQuery = true, value = """
                    UPDATE user_content us
                        SET is_watched = true, liked = :liked
                            FROM content c
                                WHERE c.url = :url
                                    AND us.user_id = :user_id
            """)
    void setWatched(@Param("url") String url, @Param("liked") Integer liked, @Param("user_id") Long userId);
}
