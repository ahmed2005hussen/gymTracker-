package com.ahmed.Hadidy.workoutPlan.service;

import com.ahmed.Hadidy.workoutPlan.dto.CreateWorkoutPlanRequest;
import com.ahmed.Hadidy.workoutPlan.dto.WorkoutPlanRequest;
import com.ahmed.Hadidy.workoutPlan.dto.WorkoutPlanResponse;

import java.util.List;

public interface WorkoutPlanService {

    WorkoutPlanResponse createWorkoutPlan(String username, CreateWorkoutPlanRequest request);

    List<WorkoutPlanResponse> listWorkoutPlan(String username);

    WorkoutPlanResponse getWorkoutPlan(String username, Long id);

    void deleteWorkoutPlan(String username, Long id);

    WorkoutPlanResponse editWorkoutPlan(String username, Long id, WorkoutPlanRequest request);


}
