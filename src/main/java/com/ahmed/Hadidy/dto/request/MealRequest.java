package com.ahmed.Hadidy.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MealRequest {

    private String name;

    private String recipe;

    private String photo;

    private Double calories;

    private Double protein;

    private Double carbs;

    private Double fats;

    private String time;

}
