package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.dto.request.CreateMealRequest;
import com.ahmed.Hadidy.dto.request.MealRequest;
import com.ahmed.Hadidy.dto.response.MealResponse;

import java.util.List;

public interface MealService {

    MealResponse createMeal(String username , Long dietPlanId , CreateMealRequest request);
    List<MealResponse> listMeal(String username , Long dietPlanId ) ;
    MealResponse getMeal(String username , Long dietPlanId , Long mealId);
    void deleteMeal(String username , Long dietPlanId , Long mealId);
    MealResponse editMeal(String username,Long dietPlanId,Long mealId, MealRequest request);
}
