package com.codeminton.tutorialapi.controller;

import com.codeminton.tutorialapi.dto.TutorialRequest;
import com.codeminton.tutorialapi.dto.TutorialResponse;
import com.codeminton.tutorialapi.dto.TutorialSummaryResponse;
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

    @GetMapping
    public ResponseEntity<ApiResponse<List<TutorialSummaryResponse>>> getAllTutorials() {
        List<TutorialSummaryResponse> tutorials = service.getAll()
                .stream()
                .map(TutorialSummaryResponse::fromEntity)
                .toList();

        ApiResponse<List<TutorialSummaryResponse>> response = new ApiResponse<>(
                true,
                "Tutorials retrieved successfully.",
                HttpStatus.OK.value(),
                tutorials
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<TutorialResponse>> getTutorial(@PathVariable String slug) {
        ApiResponse<TutorialResponse> response = new ApiResponse<>(
                true,
                "Tutorial retrieved successfully.",
                HttpStatus.OK.value(),
                TutorialResponse.fromEntity(service.getBySlug(slug))
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<TutorialResponse>> getTutorialById(@PathVariable Long id) {
        ApiResponse<TutorialResponse> response = new ApiResponse<>(
                true,
                "Tutorial retrieved successfully.",
                HttpStatus.OK.value(),
                TutorialResponse.fromEntity(service.getById(id))
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TutorialResponse>> updateTutorial(
            @PathVariable Long id,
            @Valid @RequestBody TutorialRequest request
    ) {
        ApiResponse<TutorialResponse> response = new ApiResponse<>(
                true,
                "Tutorial updated successfully.",
                HttpStatus.OK.value(),
                TutorialResponse.fromEntity(service.update(id, request))
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTutorial(@PathVariable Long id) {
        service.delete(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Tutorial deleted successfully.",
                HttpStatus.OK.value(),
                null
        );

        return ResponseEntity.ok(response);
    }
}