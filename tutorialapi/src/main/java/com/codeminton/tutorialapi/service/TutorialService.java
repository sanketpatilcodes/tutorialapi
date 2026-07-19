package com.codeminton.tutorialapi.service;

import com.codeminton.tutorialapi.dto.TutorialRequest;
import com.codeminton.tutorialapi.entity.Tutorial;
import com.codeminton.tutorialapi.repository.TutorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    private final TutorialRepository repository;

    public TutorialService(TutorialRepository repository) {
        this.repository = repository;
    }

    public Tutorial create(TutorialRequest request) {

        Tutorial tutorial = new Tutorial();
        tutorial.setQuestion(request.getQuestion());
        tutorial.setDescription(request.getDescription());

        return repository.save(tutorial);
    }

    public List<Tutorial> getAll() {
        return repository.findAll();
    }

    public Tutorial getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found"));
    }

    public Tutorial update(Long id, TutorialRequest request) {

        Tutorial tutorial = getById(id);

        tutorial.setQuestion(request.getQuestion());
        tutorial.setDescription(request.getDescription());

        return repository.save(tutorial);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}