package com.tekkenhub.backend.controllers;

import com.tekkenhub.backend.exceptions.NotFoundException;
import com.tekkenhub.backend.models.Tournament;
import com.tekkenhub.backend.models.TournamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TournamentController {
    private final Logger log = LoggerFactory.getLogger(TournamentController.class);
    private final TournamentRepository repository;

    public TournamentController(TournamentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tournaments")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Tournament> getAllPosts() {
        Collection<Tournament> c = repository.findAll();
        return c;
    }

    @PostMapping("/tournaments")
    public Tournament createTournament(@Valid @RequestBody Tournament t) {
        log.info("Request to create tournament: {}", t);
        repository.save(t);

        Optional<Tournament> persistedData = Optional.empty();
        persistedData = repository.findById(t.getId());
        return persistedData
            .orElseThrow(() -> new NotFoundException("Tournament", "id", t.getId()));
    }

    @PatchMapping("/tournaments/{id}")
    public ResponseEntity<Tournament> patchTournament(@PathVariable(value = "id") Long id, @Valid @RequestBody Tournament newT) {
        Optional<Tournament> oldT = repository.findById(id);
        if (!oldT.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Tournament tournament = oldT.get();
        System.out.println(tournament.toString());
        System.out.println(newT.toString());
        if (newT.getTitle() != null) {
            tournament.setTitle(newT.getTitle());
        }

        if (newT.getRegion() != null) {
            tournament.setRegion(newT.getRegion());
        }

        if (newT.getLocation() != null) {
            tournament.setLocation(newT.getLocation());
        }

        if (newT.getEventLink() != null) {
            tournament.setEventLink(newT.getEventLink());
        }

        if (newT.getStreamLink() != null) {
            tournament.setStreamLink(newT.getStreamLink());
        }

        if (newT.getAttendees() > 0) {
            tournament.setAttendees(newT.getAttendees());
        }

        if (newT.getStartTime() != null) {
            tournament.setStartTime(newT.getStartTime());
        }

        repository.save(tournament);
        return ResponseEntity.ok(repository.findById(tournament.getId())
                .orElseThrow(() -> new NotFoundException("Tournament", "id", id)));
    }
}
