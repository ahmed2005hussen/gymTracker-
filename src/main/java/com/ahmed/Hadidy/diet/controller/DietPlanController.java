package com.ahmed.Hadidy.diet.controller;


import com.ahmed.Hadidy.diet.dto.DietPlanResponse;
import com.ahmed.Hadidy.diet.dto.CreateDietPlanRequest;
import com.ahmed.Hadidy.diet.dto.DietPlanRequest;
import com.ahmed.Hadidy.diet.service.DietPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet-plans")
@RequiredArgsConstructor
public class DietPlanController {

    private final DietPlanService dietPlanService;

    @PostMapping
    public ResponseEntity<DietPlanResponse> createDietPlan(@Valid @RequestBody CreateDietPlanRequest request
            , Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dietPlanService.createDietPlan(authentication.getName(), request)
                );
    }


    @GetMapping
    public ResponseEntity<List<DietPlanResponse>> listDietPlan(Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(dietPlanService.listDietPlan(authentication.getName())
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<DietPlanResponse> getDietPlan
            (Authentication authentication, @PathVariable Long id) {


        return ResponseEntity.status(HttpStatus.OK).body(
                dietPlanService.getDietPlan(authentication.getName(), id)
        );

    }


    @PatchMapping("/{id}")
    public ResponseEntity<DietPlanResponse> editDietPlan
            (@PathVariable Long id, @RequestBody DietPlanRequest request,
             Authentication authentication
            ) {

        return ResponseEntity.status(HttpStatus.OK).body(
                dietPlanService.editDietPlan(authentication.getName(), id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDietPlan
            (@PathVariable Long id, Authentication authentication) {

        dietPlanService.deleteDietPlan(authentication.getName(), id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Diet Plan is Deleted"
        );

    }


}
