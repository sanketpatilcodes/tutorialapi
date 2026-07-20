package com.codeminton.tutorialapi.repository;

import com.codeminton.tutorialapi.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findAllByPublishedTrueOrderByDisplayOrderAscTitleAsc();

    List<Tutorial> findAllByTopicSlugAndPublishedTrueOrderByDisplayOrderAscTitleAsc(String topicSlug);

    Optional<Tutorial> findBySlugAndPublishedTrue(String slug);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);
}