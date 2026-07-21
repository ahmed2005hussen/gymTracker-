package com.ahmed.Hadidy.controllers;


import com.ahmed.Hadidy.dto.WorkoutDayResponse;
import com.ahmed.Hadidy.dto.WorkoutPlanResponse;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/workoutplan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    // CRUD
    final private UserRepository userRepository;
    final private WorkoutPlanRepository workoutPlanRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createWorkoutPlan(@RequestBody WorkoutPlanResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = new WorkoutPlan();
            workoutPlan.setWorkoutDays(null);
            workoutPlan.setPicture(Dto.getPicture());
            workoutPlan.setProfile(user.getProfile());
            workoutPlan.setName(Dto.getName());
            workoutPlan.setDescription(Dto.getDescription());

            WorkoutPlan saved = workoutPlanRepository.save(workoutPlan);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Workout Plan is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");

        }

    }


    @GetMapping("/list")
    public ResponseEntity<?> listWorkoutPlan(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByProfileId(user.getId());

            if (workoutPlans != null) {

                List<WorkoutPlanResponse> workoutPlanResponses = new ArrayList<>();

                for (WorkoutPlan w : workoutPlans) {

                    WorkoutPlanResponse workoutPlan = new WorkoutPlanResponse();


                    List<WorkoutDayResponse> days = new ArrayList<>();
                    for (WorkoutDay wd : w.getWorkoutDays()) {
                        WorkoutDayResponse dayDto = new WorkoutDayResponse();
                        dayDto.setName(wd.getName());
                        dayDto.setDescription(wd.getDescription());
                        dayDto.setImage(wd.getImage());
                        dayDto.setExpectedTime(wd.getExpectedTime());
                        days.add(dayDto);
                    }
                    workoutPlan.setWorkoutDays(days);


                    workoutPlan.setPicture(w.getPicture());
                    workoutPlan.setName(w.getName());
                    workoutPlan.setDescription(w.getDescription());
                    workoutPlanResponses.add(workoutPlan);
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        workoutPlanResponses
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Workout plan "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @GetMapping("list/{id}")
    public ResponseEntity<?> getWorkoutPlan(Authentication authentication, @PathVariable Long id) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByProfileId(user.getId());

            if (workoutPlans != null) {


                for (WorkoutPlan w : workoutPlans) {

                    if(w.getId() == id) {
                        WorkoutPlanResponse workoutPlan = new WorkoutPlanResponse();



                        List<WorkoutDayResponse> days = new ArrayList<>();
                        for (WorkoutDay wd : w.getWorkoutDays()) {
                            WorkoutDayResponse dayDto = new WorkoutDayResponse();
                            dayDto.setName(wd.getName());
                            dayDto.setDescription(wd.getDescription());
                            dayDto.setImage(wd.getImage());
                            dayDto.setExpectedTime(wd.getExpectedTime());
                            days.add(dayDto);
                        }
                        workoutPlan.setWorkoutDays(days);

                        workoutPlan.setPicture(w.getPicture());
                        workoutPlan.setName(w.getName());
                        workoutPlan.setDescription(w.getDescription());
                        return ResponseEntity.status(HttpStatus.OK).body(
                                workoutPlan
                        );
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        "Not Found"
                );


            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Workout plan "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteWorkoutPlan(@PathVariable Long id , Authentication authentication){


        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByProfileId(user.getId());

                for (WorkoutPlan w : workoutPlans) {

                    if(w.getId() == id) {
                        workoutPlanRepository.deleteById(id);
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
    ResponseEntity<String> editWorkoutPlan(@PathVariable Long id,@RequestBody WorkoutPlanResponse request,
                                       Authentication authentication
    ) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(id).orElseThrow(()
            -> new RuntimeException("not found"));

            if(workoutPlan.getProfile().getId() != user.getId()){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            if (request.getDescription() != null) {
                workoutPlan.setDescription(request.getDescription());
            }
            if (request.getName() != null) {
                workoutPlan.setName(request.getName());
            }
            if (request.getPicture() != null) {
                workoutPlan.setPicture(request.getPicture());
            }
            workoutPlanRepository.save(workoutPlan);
            return ResponseEntity.status(HttpStatus.OK).body(
                    "workoutPlan is Edited"
            );
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }
    }

}
