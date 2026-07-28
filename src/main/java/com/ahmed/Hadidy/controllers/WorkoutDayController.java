package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.request.CreateWorkoutDayRequest;
import com.ahmed.Hadidy.dto.request.WorkoutDayRequest;
import com.ahmed.Hadidy.dto.response.WorkoutDayResponse;
import com.ahmed.Hadidy.service.interfaces.WorkoutDayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans/{workoutPlanId}/days")
@RequiredArgsConstructor
public class WorkoutDayController {

    final private WorkoutDayService workoutDayService;

    @PostMapping
    public ResponseEntity<WorkoutDayResponse> createWorkoutDay(
            @Valid @RequestBody CreateWorkoutDayRequest request,
            @PathVariable Long workoutPlanId, Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                workoutDayService.createWorkoutDay(authentication.getName(),
                        workoutPlanId, request)
        );

    }


    @GetMapping
    public ResponseEntity<List<WorkoutDayResponse>> listWorkoutDay(Authentication authentication,
                                                                   @PathVariable long workoutPlanId) {

        return ResponseEntity.status(HttpStatus.OK).body(
                workoutDayService.listWorkoutDay(authentication.getName(), workoutPlanId)
        );

    }

    @GetMapping("/{workoutDayId}")
    public ResponseEntity<WorkoutDayResponse> getWorkoutDay(Authentication authentication,
                                                            @PathVariable Long workoutPlanId,
                                                            @PathVariable Long workoutDayId) {


        return ResponseEntity.status(HttpStatus.OK).body(
                workoutDayService.getWorkoutDay(authentication.getName(), workoutPlanId, workoutDayId)
        );


    }

    @DeleteMapping("/{workoutDayId}")
    public ResponseEntity<String> deleteWorkoutDay(@PathVariable Long workoutPlanId,
                                                   @PathVariable Long workoutDayId,
                                                   Authentication authentication) {

        workoutDayService.deleteWorkoutDay(authentication.getName(), workoutPlanId, workoutDayId);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Workout day deleted successfully "
        );

    }

    @PatchMapping("/{workoutDayId}")
    ResponseEntity<WorkoutDayResponse> editWorkoutDay(@PathVariable Long workoutPlanId,
                                                       @PathVariable Long workoutDayId,
                                                       @Valid @RequestBody WorkoutDayRequest request,
                                                       Authentication authentication
    ) {


        return ResponseEntity.status(HttpStatus.OK).body(
                workoutDayService.editWorkoutDay(authentication.getName(), workoutPlanId, workoutDayId, request)
        );
    }


}
