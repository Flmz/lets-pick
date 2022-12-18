package org.example.repository;

import org.example.model.UserContent;
import org.example.model.UserContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContentRepository extends JpaRepository<UserContent, UserContentId> {
}
