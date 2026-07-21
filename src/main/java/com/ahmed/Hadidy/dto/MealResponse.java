package com.ahmed.Hadidy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealResponse {

    private String name;

    private String recipe;

    private String photo;

    private Double calories;

    private Double protein;

    private Double carbs;

    private Double fats;

    private String time;

    private Long DietPlanId;
}
