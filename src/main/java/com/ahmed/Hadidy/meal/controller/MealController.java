package com.ahmed.Hadidy.meal.controller;

import com.ahmed.Hadidy.meal.dto.CreateMealRequest;
import com.ahmed.Hadidy.meal.dto.MealRequest;
import com.ahmed.Hadidy.meal.dto.MealResponse;
import com.ahmed.Hadidy.meal.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diet-plans/{dietPlanId}/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping(version = "1.0")
    public ResponseEntity<MealResponse> createMeal(
            @Valid @RequestBody CreateMealRequest request
            , Authentication authentication,
            @PathVariable Long dietPlanId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mealService.createMeal(authentication.getName(), dietPlanId, request));

    }

    @GetMapping(version = "1.0")
    public ResponseEntity<List<MealResponse>> listMeal(Authentication authentication
            , @PathVariable Long dietPlanId) {


        return ResponseEntity.status(HttpStatus.OK).body(
                mealService.listMeal(authentication.getName(), dietPlanId)
        );

    }

    @GetMapping(value = "/{mealId}",version = "1.0")
    public ResponseEntity<MealResponse> getMeal(Authentication authentication,
                                                @PathVariable Long dietPlanId,
                                                @PathVariable Long mealId) {

        return ResponseEntity.status(HttpStatus.OK).body(
                mealService.getMeal(authentication.getName(), dietPlanId, mealId)
        );

    }

    @DeleteMapping(value = "/{mealId}",version = "1.0")
    public ResponseEntity<String> deleteMeal(@PathVariable Long dietPlanId,
                                             @PathVariable Long mealId,
                                             Authentication authentication) {


        mealService.deleteMeal(authentication.getName(), dietPlanId, mealId);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Meal is Deleted"
        );

    }

    @PatchMapping(value = "/{mealId}",version = "1.0")
   public ResponseEntity<MealResponse> editMeal
            (@PathVariable Long dietPlanId,
             @PathVariable Long mealId,
             @Valid @RequestBody MealRequest request,
             Authentication authentication
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                mealService.editMeal(authentication.getName(),
                        dietPlanId, mealId, request)
        );

    }

}
