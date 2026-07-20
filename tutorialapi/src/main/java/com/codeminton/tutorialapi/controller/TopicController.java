package com.codeminton.tutorialapi.controller;

import com.codeminton.tutorialapi.dto.*;
import com.codeminton.tutorialapi.dto.response.ApiResponse;
import com.codeminton.tutorialapi.entity.Topic;
import com.codeminton.tutorialapi.entity.Tutorial;
import com.codeminton.tutorialapi.service.TopicService;
import com.codeminton.tutorialapi.service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;
    private final TutorialService tutorialService;

    public TopicController(TopicService topicService, TutorialService tutorialService) {
        this.topicService = topicService;
        this.tutorialService = tutorialService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TopicSummaryResponse>>> getAllTopics() {
        List<TopicSummaryResponse> topics = topicService.getAll()
                .stream()
                .map(TopicSummaryResponse::fromEntity)
                .toList();

        ApiResponse<List<TopicSummaryResponse>> response = new ApiResponse<>(
                true,
                "Topics retrieved successfully.",
                HttpStatus.OK.value(),
                topics
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<TopicDetailResponse>> getTopic(@PathVariable String slug) {
        Topic topic = topicService.getBySlug(slug);
        List<TutorialSummaryResponse> tutorials = tutorialService.getPublishedByTopicSlug(slug)
                .stream()
                .map(TutorialSummaryResponse::fromEntity)
                .toList();

        ApiResponse<TopicDetailResponse> response = new ApiResponse<>(
                true,
                "Topic retrieved successfully.",
                HttpStatus.OK.value(),
                TopicDetailResponse.fromEntity(topic, tutorials)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}/tutorials")
    public ResponseEntity<ApiResponse<List<TutorialSummaryResponse>>> getTopicTutorials(@PathVariable String slug) {
        topicService.getBySlug(slug);

        List<TutorialSummaryResponse> tutorials = tutorialService.getPublishedByTopicSlug(slug)
                .stream()
                .map(TutorialSummaryResponse::fromEntity)
                .toList();

        ApiResponse<List<TutorialSummaryResponse>> response = new ApiResponse<>(
                true,
                "Topic tutorials retrieved successfully.",
                HttpStatus.OK.value(),
                tutorials
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TopicSummaryResponse>> createTopic(@Valid @RequestBody TopicRequest request) {
        Topic topic = topicService.create(request);

        ApiResponse<TopicSummaryResponse> response = new ApiResponse<>(
                true,
                "Topic created successfully.",
                HttpStatus.CREATED.value(),
                TopicSummaryResponse.fromEntity(topic)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicSummaryResponse>> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody TopicRequest request
    ) {
        Topic topic = topicService.update(id, request);

        ApiResponse<TopicSummaryResponse> response = new ApiResponse<>(
                true,
                "Topic updated successfully.",
                HttpStatus.OK.value(),
                TopicSummaryResponse.fromEntity(topic)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Topic deleted successfully.",
                HttpStatus.OK.value(),
                null
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{topicId}/tutorials")
    public ResponseEntity<ApiResponse<TutorialResponse>> createTutorial(
            @PathVariable Long topicId,
            @Valid @RequestBody TutorialRequest request
    ) {
        Tutorial tutorial = tutorialService.create(topicId, request);

        ApiResponse<TutorialResponse> response = new ApiResponse<>(
                true,
                "Tutorial created successfully.",
                HttpStatus.CREATED.value(),
                TutorialResponse.fromEntity(tutorial)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
