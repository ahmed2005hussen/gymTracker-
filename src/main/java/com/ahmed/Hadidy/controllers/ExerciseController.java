package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.request.CreateExerciseRequest;
import com.ahmed.Hadidy.dto.request.ExerciseRequest;
import com.ahmed.Hadidy.dto.response.ExerciseResponse;
import com.ahmed.Hadidy.service.interfaces.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans/{workoutPlanId}/days/{workoutDayId}/exercises")
@RequiredArgsConstructor


public class ExerciseController {

    final private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(
            @Valid @RequestBody CreateExerciseRequest request
            , Authentication authentication,
            @PathVariable Long workoutPlanId,
            @PathVariable Long workoutDayId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                exerciseService.createExercise(authentication.getName(),
                        workoutPlanId, workoutDayId, request)
        );
    }


    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> listExercise
            (Authentication authentication,
             @PathVariable long workoutPlanId,
             @PathVariable long workoutDayId) {

        return ResponseEntity.status(HttpStatus.OK).body(
                exerciseService.listExercise(authentication.getName(),
                        workoutPlanId, workoutDayId)
        );
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponse> getExercise(Authentication authentication,
                                                        @PathVariable Long workoutPlanId,
                                                        @PathVariable Long workoutDayId,
                                                        @PathVariable Long exerciseId) {


        return ResponseEntity.status(HttpStatus.OK).body(
                exerciseService.getExercise(authentication.getName(),
                        workoutPlanId, workoutDayId, exerciseId)
        );

    }


    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long workoutPlanId,
                                                 @PathVariable Long workoutDayId,
                                                 @PathVariable Long exerciseId,
                                                 Authentication authentication) {

        exerciseService.deleteExercise(authentication.getName(),
                workoutPlanId, workoutDayId, exerciseId);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Deleted successfully"
        );
    }

    @PatchMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponse> editExercise(@PathVariable Long workoutPlanId,
                                                         @PathVariable Long workoutDayId,
                                                         @PathVariable Long exerciseId,
                                                         @Valid @RequestBody ExerciseRequest request,
                                                         Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                exerciseService.editExercise(authentication.getName(), workoutPlanId,
                        workoutDayId, exerciseId, request)

        );

    }


}
