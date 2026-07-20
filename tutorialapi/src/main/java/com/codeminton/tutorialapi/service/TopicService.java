package com.codeminton.tutorialapi.service;

import com.codeminton.tutorialapi.dto.TopicRequest;
import com.codeminton.tutorialapi.entity.Topic;
import com.codeminton.tutorialapi.exception.DuplicateResourceException;
import com.codeminton.tutorialapi.exception.ResourceNotFoundException;
import com.codeminton.tutorialapi.repository.TopicRepository;
import com.codeminton.tutorialapi.util.SlugUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Topic create(TopicRequest request) {
        String slug = resolveSlug(request.getSlug(), request.getName());
        ensureUniqueSlug(slug, null);

        Topic topic = new Topic();
        applyRequest(topic, request, slug);
        return repository.save(topic);
    }

    public List<Topic> getAll() {
        return repository.findAllByOrderByDisplayOrderAscNameAsc();
    }

    public Topic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found."));
    }

    public Topic getBySlug(String slug) {
        return repository.findBySlug(SlugUtils.toSlug(slug))
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found."));
    }

    public Topic update(Long id, TopicRequest request) {
        Topic topic = getById(id);
        String slug = resolveSlug(request.getSlug(), request.getName());
        ensureUniqueSlug(slug, id);

        applyRequest(topic, request, slug);
        return repository.save(topic);
    }

    public void delete(Long id) {
        Topic topic = getById(id);
        repository.delete(topic);
    }

    private void applyRequest(Topic topic, TopicRequest request, String slug) {
        topic.setName(request.getName().trim());
        topic.setSlug(slug);
        topic.setDescription(request.getDescription());
        topic.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
    }

    private String resolveSlug(String slug, String fallbackName) {
        String source = (slug == null || slug.isBlank()) ? fallbackName : slug;
        return SlugUtils.toSlug(source);
    }

    private void ensureUniqueSlug(String slug, Long idToExclude) {
        boolean exists = idToExclude == null
                ? repository.existsBySlug(slug)
                : repository.existsBySlugAndIdNot(slug, idToExclude);

        if (exists) {
            throw new DuplicateResourceException("Topic slug already exists.");
        }
    }
}
