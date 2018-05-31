package gr.uoa.di.recommender.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserRating {

    private Long userId;

    private Long recipeId;

    private Double rating;

    public UserRating() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
