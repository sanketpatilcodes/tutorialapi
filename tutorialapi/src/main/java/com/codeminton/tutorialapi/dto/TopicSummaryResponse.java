package com.codeminton.tutorialapi.dto;

import com.codeminton.tutorialapi.entity.Topic;

public class TopicSummaryResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private Integer displayOrder;

    public static TopicSummaryResponse fromEntity(Topic topic) {
        TopicSummaryResponse response = new TopicSummaryResponse();
        response.setId(topic.getId());
        response.setName(topic.getName());
        response.setSlug(topic.getSlug());
        response.setDescription(topic.getDescription());
        response.setDisplayOrder(topic.getDisplayOrder());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
