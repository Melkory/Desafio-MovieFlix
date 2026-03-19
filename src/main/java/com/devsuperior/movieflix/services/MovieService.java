package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> findAllPaged( String genreId, String title, Pageable pageable) {

        List<Long> genreIds = new ArrayList<>();
        if (!"0".equals(genreId)) {
            genreIds = Arrays.asList(genreId.split(",")).stream().map(Long::parseLong).toList();
        }

        Page<MovieProjection> page = movieRepository.searchMovies(genreIds, title.trim(), pageable);
        List<Long> movieIds = page.map(x -> x.getId()).toList();

        List<Movie> entities = movieRepository.searchMoviesWithGenres(movieIds);
        entities = (List<Movie>) Utils.replace(page.getContent(), entities);
        List<MovieDetailsDTO> dtos = entities.stream().map(p -> new MovieDetailsDTO(p)).toList();

        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }

}
