package com.codeminton.tutorialapi.dto;

import com.codeminton.tutorialapi.entity.Tutorial;

import java.time.LocalDateTime;

public class TutorialResponse {

    private Long id;
    private TopicSummaryResponse topic;
    private String title;
    private String slug;
    private String summary;
    private String content;
    private Integer displayOrder;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TutorialResponse fromEntity(Tutorial tutorial) {
        TutorialResponse response = new TutorialResponse();
        response.setId(tutorial.getId());
        response.setTopic(TopicSummaryResponse.fromEntity(tutorial.getTopic()));
        response.setTitle(tutorial.getTitle());
        response.setSlug(tutorial.getSlug());
        response.setSummary(tutorial.getSummary());
        response.setContent(tutorial.getContent());
        response.setDisplayOrder(tutorial.getDisplayOrder());
        response.setPublished(tutorial.getPublished());
        response.setCreatedAt(tutorial.getCreatedAt());
        response.setUpdatedAt(tutorial.getUpdatedAt());
        return response;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicSummaryResponse getTopic() {
        return topic;
    }

    public void setTopic(TopicSummaryResponse topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}