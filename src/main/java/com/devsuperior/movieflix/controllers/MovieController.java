package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MovieDetailsReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findAllPaged(
            @RequestParam(value = "genreId", defaultValue = "0") String genreId,
            @RequestParam(value = "title", defaultValue = "") String title,
            Pageable pageable) {
        Page<MovieCardDTO> page = movieService.findAllPaged(genreId, title, pageable);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO movie = movieService.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<MovieDetailsReviewDTO> findByIdReview( @PathVariable Long id) {
        MovieDetailsReviewDTO movie = movieService.findByIdReview(id);
        return ResponseEntity.ok().body(movie);
    }

}
