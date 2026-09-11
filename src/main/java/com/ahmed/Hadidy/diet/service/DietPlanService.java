package com.ahmed.Hadidy.diet.service;

import com.ahmed.Hadidy.diet.dto.DietPlanResponse;
import com.ahmed.Hadidy.diet.dto.CreateDietPlanRequest;
import com.ahmed.Hadidy.diet.dto.DietPlanRequest;

import java.util.List;

public interface DietPlanService  {
    DietPlanResponse createDietPlan(String username , CreateDietPlanRequest request);
    List<DietPlanResponse> listDietPlan(String username);
    DietPlanResponse getDietPlan(String username , Long id);
    DietPlanResponse editDietPlan(String username ,Long id ,  DietPlanRequest request);
    void deleteDietPlan(String username , Long id);
}
