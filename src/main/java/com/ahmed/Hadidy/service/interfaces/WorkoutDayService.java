package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.dto.request.CreateWorkoutDayRequest;
import com.ahmed.Hadidy.dto.request.WorkoutDayRequest;
import com.ahmed.Hadidy.dto.response.WorkoutDayResponse;

import java.util.List;

public interface WorkoutDayService {

    WorkoutDayResponse createWorkoutDay(String username, Long workoutPlanId, CreateWorkoutDayRequest request);

    List<WorkoutDayResponse> listWorkoutDay(String username, Long workoutPlanId);

    WorkoutDayResponse getWorkoutDay(String username, Long workoutPlanId, Long workoutDayId);

    void deleteWorkoutDay(String username, Long workoutPlanId, Long workoutDayId);

    WorkoutDayResponse editWorkoutDay(String username, Long workoutPlanId,
                                      Long workoutDayId, WorkoutDayRequest request);
}
