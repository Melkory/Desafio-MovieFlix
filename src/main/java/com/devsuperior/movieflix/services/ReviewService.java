package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);
    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity) {

        entity.setText(dto.getText());

        Movie movie = movieRepository.getReferenceById(dto.getMovieId());
        entity.setMovie(movie);

        User user = authService.authenticated();
        entity.setUser(user);
    }


}
