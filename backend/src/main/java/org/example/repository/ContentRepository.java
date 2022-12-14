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
            from content c
                join user_content uc on c.id = uc.content_id
                where uc.user_id = :id and c.type = :type and
                     uc.is_watched = false ORDER BY random() limit 2
            """)
    List<Content> findNotWatchedContentByUserId(@Param("id") Long id, @Param("type") String type);

    @Query(nativeQuery = true, value = "select" + VARIABLES + """
                        from content c join user_content uc on
                                c.id = uc.content_id where
                                  uc.user_id = :id
                                    and uc.liked > 0
                                        and c.type = :type ORDER BY random() limit 1
            """)
    Optional<Content> findSecondContent(@Param("id") Long id, @Param("type") String type);

//    Content findAnotherContent(@Param("id") Long id, @Param("type") String type);
}
