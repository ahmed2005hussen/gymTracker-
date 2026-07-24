package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.dto.response.DietPlanResponse;
import com.ahmed.Hadidy.dto.request.CreateDietPlanRequest;
import com.ahmed.Hadidy.dto.request.DietPlanRequest;

import java.util.List;

public interface DietPlanService  {
    DietPlanResponse createDietPlan(String username , CreateDietPlanRequest request);
    List<DietPlanResponse> listDietPlan(String username);
    DietPlanResponse getDietPlan(String username , Long id);
    DietPlanResponse editDietPlan(String username ,Long id ,  DietPlanRequest request);
    void deleteDietPlan(String username , Long id);
}
