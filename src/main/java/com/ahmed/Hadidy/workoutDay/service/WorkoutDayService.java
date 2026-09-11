package com.ahmed.Hadidy.workoutDay.service;

import com.ahmed.Hadidy.workoutDay.dto.CreateWorkoutDayRequest;
import com.ahmed.Hadidy.workoutDay.dto.WorkoutDayRequest;
import com.ahmed.Hadidy.workoutDay.dto.WorkoutDayResponse;

import java.util.List;

public interface WorkoutDayService {

    WorkoutDayResponse createWorkoutDay(String username, Long workoutPlanId, CreateWorkoutDayRequest request);

    List<WorkoutDayResponse> listWorkoutDay(String username, Long workoutPlanId);

    WorkoutDayResponse getWorkoutDay(String username, Long workoutPlanId, Long workoutDayId);

    void deleteWorkoutDay(String username, Long workoutPlanId, Long workoutDayId);

    WorkoutDayResponse editWorkoutDay(String username, Long workoutPlanId,
                                      Long workoutDayId, WorkoutDayRequest request);
}
