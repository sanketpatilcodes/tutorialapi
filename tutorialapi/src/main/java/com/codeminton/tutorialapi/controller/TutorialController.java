package com.codeminton.tutorialapi.controller;

import com.codeminton.tutorialapi.dto.TutorialRequest;
import com.codeminton.tutorialapi.entity.Tutorial;
import com.codeminton.tutorialapi.dto.response.ApiResponse;
import com.codeminton.tutorialapi.service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService service;

    public TutorialController(TutorialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Tutorial>> createTutorial(
            @Valid @RequestBody TutorialRequest request) {

        Tutorial tutorial = service.create(request);

        ApiResponse<Tutorial> response =
                new ApiResponse<>(
                        true,
                        "Tutorial created successfully.",
                        HttpStatus.CREATED.value(),
                        tutorial);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Tutorial>>> getAllTutorials() {

        List<Tutorial> tutorials = service.getAll();

        ApiResponse<List<Tutorial>> response =
                new ApiResponse<>(
                        true,
                        "Tutorials retrieved successfully.",
                        HttpStatus.OK.value(),
                        tutorials);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tutorial>> getTutorial(
            @PathVariable Long id) {

        Tutorial tutorial = service.getById(id);

        ApiResponse<Tutorial> response =
                new ApiResponse<>(
                        true,
                        "Tutorial retrieved successfully.",
                        HttpStatus.OK.value(),
                        tutorial);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Tutorial>> updateTutorial(
            @PathVariable Long id,
            @Valid @RequestBody TutorialRequest request) {

        Tutorial tutorial = service.update(id, request);

        ApiResponse<Tutorial> response =
                new ApiResponse<>(
                        true,
                        "Tutorial updated successfully.",
                        HttpStatus.OK.value(),
                        tutorial);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTutorial(
            @PathVariable Long id) {

        service.delete(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Tutorial deleted successfully.",
                        HttpStatus.OK.value(),
                        null);

        return ResponseEntity.ok(response);
    }
}