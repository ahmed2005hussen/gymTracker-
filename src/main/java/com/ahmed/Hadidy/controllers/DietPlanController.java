package com.ahmed.Hadidy.controllers;


import com.ahmed.Hadidy.dto.DietPlanResponse;
import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.DietPlanRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dietplan")
@RequiredArgsConstructor
public class DietPlanController {


    private final DietPlanRepository dietPlanRepository;
    final private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<?> createSupplement(@RequestBody DietPlanResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            DietPlan dietPlan = new DietPlan();

            dietPlan.setTitle(Dto.getTitle());
            dietPlan.setProfile(user.getProfile());
            dietPlan.setDescription(Dto.getDescription());

            DietPlan saved = dietPlanRepository.save(dietPlan);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Diet Plan is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");

        }

    }


    @GetMapping("/list")
    public ResponseEntity<?> listDietPlan(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<DietPlan> dietPlans = dietPlanRepository.findByProfileId(user.getId());

            if (dietPlans != null) {

                List<DietPlanResponse> dietPlanResponses = new ArrayList<>();

                for (DietPlan d : dietPlans) {

                    DietPlanResponse dietPlanResponse = new DietPlanResponse();


                    dietPlanResponse.setTitle(d.getTitle());
                    dietPlanResponse.setDescription(d.getDescription());

                    dietPlanResponses.add(dietPlanResponse);
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        dietPlanResponses
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have diet Plans "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @GetMapping("list/{id}")
    public ResponseEntity<?> getDietPlan
            (Authentication authentication, @PathVariable Long id) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<DietPlan> dietPlans = dietPlanRepository.findByProfileId(user.getId());

            if (dietPlans != null) {


                for (DietPlan d : dietPlans) {

                    if (d.getId() == id) {
                        DietPlanResponse dietPlanResponse = new DietPlanResponse();

                        dietPlanResponse.setTitle(d.getTitle());
                        dietPlanResponse.setDescription(d.getDescription());
                        return ResponseEntity.status(HttpStatus.OK).body(
                                dietPlanResponse
                        );
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        "Not Found"
                );


            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have diet Plan "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDietPlan
            (@PathVariable Long id , Authentication authentication){


        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<DietPlan> dietPlans = dietPlanRepository.findByProfileId(user.getId());

            for (DietPlan d : dietPlans) {

                if(d.getId() == id) {
                    dietPlanRepository.deleteById(id);
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


    @PatchMapping("/edit/{id}")
    ResponseEntity<String> editDietPlan
            (@PathVariable Long id,@RequestBody DietPlanResponse request,
             Authentication authentication
            ) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not found")
            );

            DietPlan dietPlan = dietPlanRepository.findById(id).orElseThrow(()
                    -> new RuntimeException("not found"));

            if(dietPlan.getProfile().getId() != user.getId()){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            if (request.getDescription() != null) {
                dietPlan.setDescription(request.getDescription());
            }
            if (request.getTitle() != null) {
                dietPlan.setTitle(request.getTitle());
            }

            dietPlanRepository.save(dietPlan);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "diet Plan is Edited"
            );
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }
    }

}
