package com.ahmed.Hadidy.controllers;


import com.ahmed.Hadidy.dto.request.CreateWorkoutPlanRequest;
import com.ahmed.Hadidy.dto.request.WorkoutPlanRequest;
import com.ahmed.Hadidy.dto.response.WorkoutPlanResponse;
import com.ahmed.Hadidy.service.interfaces.WorkoutPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    final private WorkoutPlanService workoutPlanService;

    @PostMapping
    public ResponseEntity<WorkoutPlanResponse> createWorkoutPlan(@Valid @RequestBody CreateWorkoutPlanRequest request
            , Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                workoutPlanService.createWorkoutPlan(authentication.getName(), request)
        );

    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlanResponse>> listWorkoutPlan(Authentication authentication) {


        return ResponseEntity.status(HttpStatus.OK).body(
                workoutPlanService.listWorkoutPlan(authentication.getName())
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponse> getWorkoutPlan(Authentication authentication,
                                                              @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(
                workoutPlanService.getWorkoutPlan(authentication.getName(), id)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkoutPlan(@PathVariable Long id,
                                                    Authentication authentication) {

        workoutPlanService.deleteWorkoutPlan(authentication.getName(), id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "deleted successful"
        );


    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponse> editWorkoutPlan(@PathVariable Long id
            , @RequestBody WorkoutPlanRequest request, Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                workoutPlanService.editWorkoutPlan(authentication.getName(), id, request)
        );

    }
}

