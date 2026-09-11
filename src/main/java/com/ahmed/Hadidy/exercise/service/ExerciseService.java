package com.ahmed.Hadidy.exercise.service;


import com.ahmed.Hadidy.exercise.dto.CreateExerciseRequest;
import com.ahmed.Hadidy.exercise.dto.ExerciseRequest;
import com.ahmed.Hadidy.exercise.dto.ExerciseResponse;

import java.util.List;

public interface ExerciseService {


    ExerciseResponse createExercise(String username , Long workoutPlanId ,
                                    Long workoutDayId , CreateExerciseRequest request);

    List<ExerciseResponse> listExercise(String username , Long workoutPlanId ,
                                        Long workoutDayId);

    ExerciseResponse getExercise(String username , Long workoutPlanId ,
                                 Long workoutDayId , Long exerciseID);


    void deleteExercise(String username , Long workoutPlanId ,
                        Long workoutDayId  , Long exerciseID);


    ExerciseResponse editExercise(String username , Long workoutPlanId ,
                                  Long workoutDayId, Long exerciseID , ExerciseRequest request);



}
