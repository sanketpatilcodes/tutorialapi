package com.codeminton.tutorialapi.service;

import com.codeminton.tutorialapi.dto.TutorialRequest;
import com.codeminton.tutorialapi.entity.Topic;
import com.codeminton.tutorialapi.entity.Tutorial;
import com.codeminton.tutorialapi.exception.DuplicateResourceException;
import com.codeminton.tutorialapi.exception.ResourceNotFoundException;
import com.codeminton.tutorialapi.repository.TutorialRepository;
import com.codeminton.tutorialapi.util.SlugUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    private final TutorialRepository repository;
    private final TopicService topicService;

    public TutorialService(TutorialRepository repository, TopicService topicService) {
        this.repository = repository;
        this.topicService = topicService;
    }

    public Tutorial create(Long topicId, TutorialRequest request) {
        Topic topic = topicService.getById(topicId);
        String slug = resolveSlug(request.getSlug(), request.getTitle());
        ensureUniqueSlug(slug, null);

        Tutorial tutorial = new Tutorial();
        tutorial.setTopic(topic);
        applyRequest(tutorial, request, slug);

        return repository.save(tutorial);
    }

    public List<Tutorial> getAll() {
        return repository.findAllByPublishedTrueOrderByDisplayOrderAscTitleAsc();
    }

    public List<Tutorial> getPublishedByTopicSlug(String topicSlug) {
        return repository.findAllByTopicSlugAndPublishedTrueOrderByDisplayOrderAscTitleAsc(
                SlugUtils.toSlug(topicSlug)
        );
    }

    public Tutorial getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found."));
    }

    public Tutorial getBySlug(String slug) {
        return repository.findBySlugAndPublishedTrue(SlugUtils.toSlug(slug))
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found."));
    }

    public Tutorial update(Long id, TutorialRequest request) {
        Tutorial tutorial = getById(id);
        String slug = resolveSlug(request.getSlug(), request.getTitle());
        ensureUniqueSlug(slug, id);

        applyRequest(tutorial, request, slug);

        return repository.save(tutorial);
    }

    public void delete(Long id) {
        Tutorial tutorial = getById(id);
        repository.delete(tutorial);
    }

    private void applyRequest(Tutorial tutorial, TutorialRequest request, String slug) {
        tutorial.setTitle(request.getTitle().trim());
        tutorial.setSlug(slug);
        tutorial.setSummary(request.getSummary());
        tutorial.setContent(request.getContent().trim());
        tutorial.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        tutorial.setPublished(request.getPublished() == null ? Boolean.TRUE : request.getPublished());
    }

    private String resolveSlug(String slug, String fallbackTitle) {
        String source = (slug == null || slug.isBlank()) ? fallbackTitle : slug;
        return SlugUtils.toSlug(source);
    }

    private void ensureUniqueSlug(String slug, Long idToExclude) {
        boolean exists = idToExclude == null
                ? repository.existsBySlug(slug)
                : repository.existsBySlugAndIdNot(slug, idToExclude);

        if (exists) {
            throw new DuplicateResourceException("Tutorial slug already exists.");
        }
    }
}