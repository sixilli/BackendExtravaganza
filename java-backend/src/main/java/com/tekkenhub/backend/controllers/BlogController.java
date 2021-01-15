package com.tekkenhub.backend.controllers;

import java.util.Collection;
import java.util.Optional;

import com.tekkenhub.backend.exceptions.NotFoundException;
import com.tekkenhub.backend.models.BlogPost;
import com.tekkenhub.backend.models.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {
    private final Logger log = LoggerFactory.getLogger(TournamentController.class);
    private final BlogRepository repository;

    public BlogController(BlogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/blog")
    @ResponseStatus(HttpStatus.OK)
    public Collection<BlogPost> getAllPosts() {
        Collection<BlogPost> c = repository.findAll();
        return c;
    }

    @PostMapping("/blog")
    public BlogPost createPost(@Valid @RequestBody BlogPost bp) {
        log.info("Request to create blog post: {}", bp);
        repository.save(bp);

        Optional<BlogPost> persistedData = Optional.empty();
        persistedData = repository.findById(bp.getId());
        return persistedData
                .orElseThrow(() -> new NotFoundException("Tournament", "id", bp.getId()));
    }

    @PatchMapping("/blog")
    public BlogPost patchPost(@Valid @RequestBody BlogPost newBp) {
        return newBp;
    }
}
