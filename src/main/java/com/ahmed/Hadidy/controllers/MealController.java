package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.MealResponse;
import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.entity.Meal;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.DietPlanRepository;
import com.ahmed.Hadidy.repository.MealRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;
    final private UserRepository userRepository;
    final private DietPlanRepository dietPlanRepository;


    @PostMapping("/create")
    public ResponseEntity<?> createMeal(
            @RequestBody MealResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            Meal meal = new Meal();

            DietPlan dietPlan = dietPlanRepository.findById(Dto.getDietPlanId()).orElseThrow(
                    () -> new RuntimeException("Not Found")
            );

            if (dietPlan.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            meal.setDietPlan(dietPlan);
            meal.setRecipe(Dto.getRecipe());
            meal.setPhoto(Dto.getPhoto());

            meal.setName(Dto.getName());
            meal.setCalories(Dto.getCalories());

            meal.setCarbs(Dto.getCarbs());
            meal.setProtein(Dto.getProtein());
            meal.setFats(Dto.getFats());
            meal.setTime(Dto.getTime());


            Meal saved = mealRepository.save(meal);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Meal is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");

        }

    }


    @GetMapping("/list/{dietPlanId}")
    public ResponseEntity<?> listMeal(Authentication authentication, @PathVariable Long dietPlanId) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );
            DietPlan dietPlan = dietPlanRepository.findById(dietPlanId).orElseThrow(
                    () -> new RuntimeException("Not Found")
            );

            if (dietPlan.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }


            List<Meal> meals = mealRepository.findByDietPlanId(dietPlanId);

            if (meals != null) {

                List<MealResponse> mealResponses = new ArrayList<>();

                for (Meal m : meals) {

                    MealResponse meal = new MealResponse();

                    meal.setDietPlanId(dietPlanId);
                    meal.setRecipe(m.getRecipe());
                    meal.setPhoto(m.getPhoto());

                    meal.setName(m.getName());
                    meal.setCalories(m.getCalories());

                    meal.setCarbs(m.getCarbs());
                    meal.setProtein(m.getProtein());
                    meal.setFats(m.getFats());
                    meal.setTime(m.getTime());

                    mealResponses.add(meal);
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        mealResponses
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Meal "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @GetMapping("list/{dietPlanId}/{mealId}")
    public ResponseEntity<?> getMeal
            (Authentication authentication, @PathVariable Long dietPlanId, @PathVariable Long mealId) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );
            DietPlan dietPlan = dietPlanRepository.findById(dietPlanId).orElseThrow(
                    () -> new RuntimeException("Not Found")
            );

            if (dietPlan.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }


            List<Meal> meals = mealRepository.findByDietPlanId(dietPlanId);

            if (meals != null) {

                for (Meal m : meals) {

                    if (m.getId() == mealId) {
                        MealResponse meal = new MealResponse();


                        meal.setDietPlanId(dietPlanId);
                        meal.setRecipe(m.getRecipe());
                        meal.setPhoto(m.getPhoto());

                        meal.setName(m.getName());
                        meal.setCalories(m.getCalories());

                        meal.setCarbs(m.getCarbs());
                        meal.setProtein(m.getProtein());
                        meal.setFats(m.getFats());
                        meal.setTime(m.getTime());

                        return ResponseEntity.status(HttpStatus.OK).body(
                                meal
                        );
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        "Not Found"
                );


            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Meal"
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @DeleteMapping("delete/{dietPlanId}/{mealId}")
    public ResponseEntity<?> deleteMeal
            (@PathVariable Long dietPlanId, @PathVariable Long mealId
                    , Authentication authentication) {


        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );
            DietPlan dietPlan = dietPlanRepository.findById(dietPlanId).orElseThrow(
                    () -> new RuntimeException("Not Found")
            );

            if (dietPlan.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            List<Meal> meals = mealRepository.findByDietPlanId(dietPlanId);

            for (Meal m : meals) {

                if (m.getId() == mealId) {
                    mealRepository.deleteById(mealId);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            "was Deleted"
                    );
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Not Found"
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @PatchMapping("/edit/{dietPlanId}/{mealId}")
    ResponseEntity<String> editSupplement
            (@PathVariable Long dietPlanId, @PathVariable Long mealId,
             @RequestBody MealResponse request,
             Authentication authentication
            ) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );
            DietPlan dietPlan = dietPlanRepository.findById(dietPlanId).orElseThrow(
                    () -> new RuntimeException("Not Found")
            );

            if (dietPlan.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            List<Meal> meals = mealRepository.findByDietPlanId(dietPlanId);

            for (Meal m : meals) {
                if (m.getId() == mealId) {
                    if (request.getRecipe() != null) {
                        m.setRecipe(request.getRecipe());
                    }
                    if (request.getName() != null) {
                        m.setName(request.getName());
                    }
                    if (request.getPhoto() != null) {
                        m.setPhoto(request.getPhoto());
                    }
                    if (request.getCalories() != null) {
                        m.setCalories(request.getCalories());
                    }
                    if (request.getProtein() != null) {
                        m.setProtein(request.getProtein());
                    }
                    if (request.getCarbs() != null) {
                        m.setCarbs(request.getCarbs());
                    }
                    if (request.getFats() != null) {
                        m.setFats(request.getFats());
                    }
                    if (request.getTime() != null) {
                        m.setTime(request.getTime());
                    }
                    mealRepository.save(m);

                    return ResponseEntity.status(HttpStatus.OK).body(
                            "supplement is Edited"
                    );
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Not Found"
            );

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }


    }

}
