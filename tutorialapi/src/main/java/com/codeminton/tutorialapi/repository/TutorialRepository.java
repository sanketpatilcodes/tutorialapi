package com.codeminton.tutorialapi.repository;

import com.codeminton.tutorialapi.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}