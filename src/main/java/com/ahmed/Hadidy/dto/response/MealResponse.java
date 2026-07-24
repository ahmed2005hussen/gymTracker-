package com.ahmed.Hadidy.dto.response;

import com.ahmed.Hadidy.entity.Meal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MealResponse {

    private Long id ;

    private String name;

    private String recipe;

    private String photo;

    private Double calories;

    private Double protein;

    private Double carbs;

    private Double fats;

    private String time;

    public MealResponse(Meal meal){

        this.id = meal.getId() ;

        this.name = meal.getName();

        this.recipe = meal.getRecipe();

        this.photo = meal.getPhoto();

        this.calories = meal.getCalories();

        this.protein = meal.getProtein();

        this.carbs = meal.getCarbs();

        this.fats = meal.getFats();

        this.time = meal.getTime();

    }

}
