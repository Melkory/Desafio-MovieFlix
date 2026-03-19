package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieDetailsDTO>> findAllPaged(
            @RequestParam(value = "genreId", defaultValue = "0") String genreId,
            @RequestParam(value = "title", defaultValue = "") String title,
            Pageable pageable) {
        Page<MovieDetailsDTO> page = movieService.findAllPaged(genreId, title, pageable);
        return ResponseEntity.ok().body(page);
    }

}
