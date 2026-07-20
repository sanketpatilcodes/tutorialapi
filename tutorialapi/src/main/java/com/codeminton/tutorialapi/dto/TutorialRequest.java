package com.codeminton.tutorialapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TutorialRequest {

    @NotBlank(message = "Tutorial title is required.")
    @Size(max = 200, message = "Tutorial title must be at most 200 characters.")
    private String title;

    @Size(max = 200, message = "Tutorial slug must be at most 200 characters.")
    private String slug;

    @Size(max = 2000, message = "Tutorial summary must be at most 2000 characters.")
    private String summary;

    @NotBlank(message = "Tutorial content is required.")
    private String content;

    @PositiveOrZero(message = "Display order must be zero or greater.")
    private Integer displayOrder = 0;

    private Boolean published = Boolean.TRUE;

    // Getters and Setters
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
}