package com.ahmed.Hadidy.meal.service;


import com.ahmed.Hadidy.meal.dto.CreateMealRequest;
import com.ahmed.Hadidy.meal.dto.MealRequest;
import com.ahmed.Hadidy.meal.dto.MealResponse;

import java.util.List;

public interface MealService {

    MealResponse createMeal(String username , Long dietPlanId , CreateMealRequest request);
    List<MealResponse> listMeal(String username , Long dietPlanId ) ;
    MealResponse getMeal(String username , Long dietPlanId , Long mealId);
    void deleteMeal(String username , Long dietPlanId , Long mealId);
    MealResponse editMeal(String username,Long dietPlanId,Long mealId, MealRequest request);
}
