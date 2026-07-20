package com.codeminton.tutorialapi.dto;

import com.codeminton.tutorialapi.entity.Tutorial;

public class TutorialSummaryResponse {

    private Long id;
    private String title;
    private String slug;
    private String summary;
    private Integer displayOrder;

    public static TutorialSummaryResponse fromEntity(Tutorial tutorial) {
        TutorialSummaryResponse response = new TutorialSummaryResponse();
        response.setId(tutorial.getId());
        response.setTitle(tutorial.getTitle());
        response.setSlug(tutorial.getSlug());
        response.setSummary(tutorial.getSummary());
        response.setDisplayOrder(tutorial.getDisplayOrder());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
