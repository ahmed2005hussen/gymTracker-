package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.dto.request.CreateWorkoutPlanRequest;
import com.ahmed.Hadidy.dto.request.WorkoutPlanRequest;
import com.ahmed.Hadidy.dto.response.WorkoutPlanResponse;

import java.util.List;

public interface WorkoutPlanService {

    WorkoutPlanResponse createWorkoutPlan(String username, CreateWorkoutPlanRequest request);

    List<WorkoutPlanResponse> listWorkoutPlan(String username);

    WorkoutPlanResponse getWorkoutPlan(String username, Long id);

    void deleteWorkoutPlan(String username, Long id);

    WorkoutPlanResponse editWorkoutPlan(String username, Long id, WorkoutPlanRequest request);


}
