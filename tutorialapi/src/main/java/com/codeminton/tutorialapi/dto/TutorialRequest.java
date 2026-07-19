package com.codeminton.tutorialapi.dto;

import jakarta.validation.constraints.NotBlank;

public class TutorialRequest {

    @NotBlank
    private String question;

    @NotBlank
    private String description;

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}