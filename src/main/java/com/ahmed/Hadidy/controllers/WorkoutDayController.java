package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.WorkoutDayResponse;

import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/workoutday")
@RequiredArgsConstructor
public class WorkoutDayController {


    // CRUD
    final private UserRepository userRepository;
    final private WorkoutPlanRepository workoutPlanRepository;
    final private WorkoutDayRepository workoutDayRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createWorkoutDay(@RequestBody WorkoutDayResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan w = workoutPlanRepository.findById(Dto.getWorkoutPlanId()).orElseThrow(
                    () -> new RuntimeException("not found")
            );

            if (w.getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            WorkoutDay workoutDay = new WorkoutDay();
            workoutDay.setWorkoutPlan(w);
            workoutDay.setImage(Dto.getImage());
            workoutDay.setName(Dto.getName());
            workoutDay.setDescription(Dto.getDescription());
            workoutDay.setExpectedTime(Dto.getExpectedTime());
            workoutDay.setTotalRepeat(Dto.getTotalRepeat());
            workoutDay.setTotalExercises(Dto.getTotalExercises());
            WorkoutDay saved = workoutDayRepository.save(workoutDay);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Workout Day is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");

        }

    }


    @GetMapping("/list/{workoutplanid}")
    public ResponseEntity<?> listWorkoutPlan(Authentication authentication, @PathVariable long workoutplanid) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutplanid).orElseThrow(
                    () -> new RuntimeException("Not found")
            );

            if (!workoutPlan.getProfile().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not Found "
                );
            }

            List<WorkoutDayResponse> result = new ArrayList<>();


            for (WorkoutDay w : workoutPlan.getWorkoutDays()) {


                WorkoutDayResponse dto = new WorkoutDayResponse();

                dto.setImage(w.getImage());


                // set Exercises
                //  dto.setExercises(w.getExercises());
                dto.setName(w.getName());
                dto.setDescription(w.getDescription());
                dto.setTotalRepeat(w.getTotalRepeat());
                dto.setTotalExercises(w.getTotalExercises());
                dto.setExpectedTime(w.getExpectedTime());

                result.add(dto);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    result
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }

    @GetMapping("/list/{workoutPlanId}/{workoutDayId}")
    public ResponseEntity<?> getWorkoutDay(Authentication authentication,
                                           @PathVariable Long workoutPlanId,
                                           @PathVariable Long workoutDayId) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                    .orElseThrow(() -> new RuntimeException("Not Found"));

            if (!workoutPlan.getProfile().getId().equals(user.getProfile().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
            }

            for (WorkoutDay wd : workoutPlan.getWorkoutDays()) {
                if (wd.getId().equals(workoutDayId)) {
                    WorkoutDayResponse dto = new WorkoutDayResponse();
                    dto.setName(wd.getName());
                    dto.setDescription(wd.getDescription());
                    dto.setImage(wd.getImage());
                    dto.setExpectedTime(wd.getExpectedTime());
                    dto.setTotalRepeat(wd.getTotalRepeat());
                    dto.setTotalExercises(wd.getTotalExercises());
                    //   dto.setExercises(wd.getExercises());
                    // exercises
                    return ResponseEntity.status(HttpStatus.OK).body(dto);
                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout Day Not Found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @DeleteMapping("delete/{workoutPlanId}/{workoutDayId}")
    public ResponseEntity<?> deleteWorkoutDay(@PathVariable Long workoutPlanId,
                                              @PathVariable Long workoutDayId,
                                              Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                    .orElseThrow(() -> new RuntimeException("Not Found"));

            if (!workoutPlan.getProfile().getId().equals(user.getProfile().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
            }

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutPlanId);


            for (WorkoutDay w : workoutDays) {

                if (w.getId() == workoutDayId) {
                    workoutDayRepository.deleteById(workoutDayId);
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

    @PatchMapping("/edit/{workoutPlanId}/{workoutDayId}")
    ResponseEntity<String> editWorkoutPlan(@PathVariable Long workoutPlanId,
                                           @PathVariable Long workoutDayId
            , @RequestBody WorkoutDayResponse request,
                                           Authentication authentication
    ) {
        try {

            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                    .orElseThrow(() -> new RuntimeException("Not Found"));

            if (!workoutPlan.getProfile().getId().equals(user.getProfile().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorized");
            }

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutPlanId);


            for (WorkoutDay w : workoutDays) {

                if (w.getId() == workoutDayId) {


                    if (request.getImage() != null) {
                        w.setImage(request.getImage());
                    }
                    if (request.getName() != null) {
                        w.setName(request.getName());
                    }
                    if (request.getDescription() != null) {
                        w.setDescription(request.getDescription());
                    }
                    if (request.getExpectedTime() != null) {
                        w.setExpectedTime(request.getExpectedTime());
                    }
                    if (request.getTotalRepeat() != null) {
                        w.setTotalRepeat(request.getTotalRepeat());
                    }
                    if (request.getTotalExercises() != null) {
                        w.setTotalExercises(request.getTotalExercises());
                    }
                    workoutDayRepository.save(w);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            "workoutDay is Edited"
                    );

                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Not found"
            );



        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }
    }


}
