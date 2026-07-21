package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.ExerciseResponse;
import com.ahmed.Hadidy.entity.Exercise;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.repository.ExerciseRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor


public class ExerciseController {


    // CRUD
    final private UserRepository userRepository;
    final private WorkoutPlanRepository workoutPlanRepository;
    final private WorkoutDayRepository workoutDayRepository;
    final private ExerciseRepository exerciseRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createExercise(@RequestBody ExerciseResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutDay w = workoutDayRepository.findById(Dto.getWorkoutDayId()).orElseThrow(
                    () -> new RuntimeException("not found")
            );

            if (w.getWorkoutPlan().getProfile().getId() != user.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            Exercise exercise = new Exercise();
            exercise.setRepeat(Dto.getRepeat());
            exercise.setSets(Dto.getSets());
            exercise.setName(Dto.getName());
            exercise.setDescription(Dto.getDescription());
            exercise.setPicture(Dto.getPicture());
            exercise.setWorkoutDay(w);


            Exercise saved = exerciseRepository.save(exercise);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Exercise is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error" + e.getMessage());

        }

    }


    @GetMapping("/list/{workoutplanid}/{workoutDayid}")
    public ResponseEntity<?> listExercise(Authentication authentication,
                                          @PathVariable long workoutplanid
            , @PathVariable long workoutDayid) {
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

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutplanid);

            Set<Exercise> result = new HashSet<>();

            for (WorkoutDay w : workoutDays) {
                if (w.getId() == workoutDayid) {
                    result = w.getExercises();
                    break;
                }
            }

            List<ExerciseResponse> res = new ArrayList<>();

            for (Exercise e : result) {


                ExerciseResponse dto = new ExerciseResponse();

                dto.setRepeat(e.getRepeat());
                dto.setSets(e.getSets());
                dto.setName(e.getName());
                dto.setDescription(e.getDescription());
                dto.setPicture(e.getPicture());
                dto.setWorkoutDayId(workoutDayid);

                res.add(dto);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    res
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }

    @GetMapping("/list/{workoutPlanId}/{workoutDayId}/{exerciseId}")
    public ResponseEntity<?> getExercise(Authentication authentication,
                                         @PathVariable Long workoutPlanId,
                                         @PathVariable Long workoutDayId,
                                         @PathVariable Long exerciseId) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId).orElseThrow(
                    () -> new RuntimeException("Not found")
            );

            if (!workoutPlan.getProfile().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not Found "
                );
            }

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutPlanId);

            Set<Exercise> result = new HashSet<>();

            for (WorkoutDay w : workoutDays) {
                if (w.getId() == workoutDayId) {
                    result = w.getExercises();
                    break;
                }
            }

            for (Exercise e : result) {

                if (e.getId() == exerciseId) {
                    ExerciseResponse dto = new ExerciseResponse();

                    dto.setRepeat(e.getRepeat());
                    dto.setSets(e.getSets());
                    dto.setName(e.getName());
                    dto.setDescription(e.getDescription());
                    dto.setPicture(e.getPicture());
                    dto.setWorkoutDayId(workoutDayId);

                    return ResponseEntity.status(HttpStatus.OK).body(
                            dto
                    );
                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout Day Not Found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }


    @DeleteMapping("delete/{workoutPlanId}/{workoutDayId}/{exerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long workoutPlanId,
                                            @PathVariable Long workoutDayId,
                                            @PathVariable Long exerciseId,
                                            Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId).orElseThrow(
                    () -> new RuntimeException("Not found")
            );

            if (!workoutPlan.getProfile().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not Found "
                );
            }

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutPlanId);

            Set<Exercise> result = new HashSet<>();

            for (WorkoutDay w : workoutDays) {
                if (w.getId() == workoutDayId) {
                    result = w.getExercises();
                    break;
                }
            }

            for (Exercise e : result) {

                if (e.getId() == exerciseId) {

                    exerciseRepository.deleteById(e.getId());
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

    @PatchMapping("/edit/{workoutPlanId}/{workoutDayId}/{exerciseId}")
    ResponseEntity<String> editExercise(@PathVariable Long workoutPlanId,
                                        @PathVariable Long workoutDayId,
                                        @PathVariable Long exerciseId
            , @RequestBody ExerciseResponse request,
                                        Authentication authentication
    ) {
        try {

            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId).orElseThrow(
                    () -> new RuntimeException("Not found")
            );

            if (!workoutPlan.getProfile().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not Found "
                );
            }

            List<WorkoutDay> workoutDays = workoutDayRepository.findByWorkoutPlanId(workoutPlanId);

            Set<Exercise> result = new HashSet<>();

            for (WorkoutDay w : workoutDays) {
                if (w.getId() == workoutDayId) {
                    result = w.getExercises();
                    break;
                }
            }


            for (Exercise e : result) {

                if (e.getId() == exerciseId) {


                    if (request.getRepeat() != null) {
                        e.setRepeat(request.getRepeat());
                    }
                    if (request.getName() != null) {
                        e.setName(request.getName());
                    }
                    if (request.getDescription() != null) {
                        e.setDescription(request.getDescription());
                    }
                    if (request.getSets() != null) {
                        e.setSets(request.getSets());
                    }
                    if (request.getPicture() != null) {
                        e.setPicture(request.getPicture());
                    }

                    exerciseRepository.save(e);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            "exercise is Edited"
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
