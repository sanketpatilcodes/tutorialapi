package com.codeminton.tutorialapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TopicRequest {

    @NotBlank(message = "Topic name is required.")
    @Size(max = 150, message = "Topic name must be at most 150 characters.")
    private String name;

    @Size(max = 150, message = "Topic slug must be at most 150 characters.")
    private String slug;

    @Size(max = 1000, message = "Topic description must be at most 1000 characters.")
    private String description;

    @PositiveOrZero(message = "Display order must be zero or greater.")
    private Integer displayOrder = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
