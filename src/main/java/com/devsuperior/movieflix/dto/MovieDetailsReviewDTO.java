package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsReviewDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
    private List<ReviewDTO> reviews = new ArrayList<>();

    public MovieDetailsReviewDTO() {
    }

    public MovieDetailsReviewDTO( Long id, String title, String subTitle, Integer year, String imgUrl ) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
    }

    public MovieDetailsReviewDTO( Movie entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.subTitle = entity.getSubTitle();
        this.year = entity.getYear();
        this.imgUrl = entity.getImgUrl();
    }

    public MovieDetailsReviewDTO( Movie entity, List<Review> reviews) {
        this(entity);
        reviews.forEach(rev -> this.reviews.add(new ReviewDTO(rev)));
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews( List<ReviewDTO> reviews ) {
        this.reviews = reviews;
    }
}
