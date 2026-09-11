package com.ahmed.Hadidy.exercise.service.impl;


import com.ahmed.Hadidy.exercise.dto.CreateExerciseRequest;
import com.ahmed.Hadidy.exercise.dto.ExerciseRequest;
import com.ahmed.Hadidy.exercise.dto.ExerciseResponse;
import com.ahmed.Hadidy.exercise.entity.Exercise;
import com.ahmed.Hadidy.user.entity.HadidyUser;
import com.ahmed.Hadidy.workoutDay.entity.WorkoutDay;
import com.ahmed.Hadidy.workoutPlan.entity.WorkoutPlan;
import com.ahmed.Hadidy.exception.DataNotExist;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.exercise.repository.ExerciseRepository;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.workoutDay.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.workoutPlan.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutDayRepository workoutDayRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;


    private HadidyUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    public ExerciseResponse createExercise(String username,
                                           Long workoutPlanId,
                                           Long workoutDayId,
                                           CreateExerciseRequest request) {
        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout plan not exist"));

        WorkoutDay workoutDay = workoutDayRepository.
                findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("Workout Day not exist"));

        Exercise exercise = new Exercise();
        exercise.setWorkoutDay(workoutDay);

        exercise.setName(request.getName());
        exercise.setDescription(request.getDescription());
        exercise.setPicture(request.getPicture());
        exercise.setRepeat(request.getRepeat());
        exercise.setSets(request.getSets());

        Exercise Saved = exerciseRepository.save(exercise);

        return new ExerciseResponse(Saved);
    }

    @Override
    public List<ExerciseResponse> listExercise(String username, Long workoutPlanId,
                                               Long workoutDayId) {

        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout plan not exist"));

        WorkoutDay workoutDay = workoutDayRepository.
                findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("Workout Day not exist"));


        return exerciseRepository.findAllByWorkoutDayId(workoutDay.getId())
                .stream().map(ExerciseResponse::new).toList();

    }

    @Override
    public ExerciseResponse getExercise(String username, Long workoutPlanId,
                                        Long workoutDayId, Long exerciseID) {

        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout plan not exist"));

        WorkoutDay workoutDay = workoutDayRepository.
                findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("Workout Day not exist"));

        Exercise exercise = exerciseRepository.findByIdAndWorkoutDayId(exerciseID, workoutDay.getId())
                .orElseThrow(() -> new DataNotExist("Exercise not Exist"));

        return new ExerciseResponse(exercise);

    }

    @Override
    public void deleteExercise(String username, Long workoutPlanId,
                               Long workoutDayId, Long exerciseID) {


        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout plan not exist"));

        WorkoutDay workoutDay = workoutDayRepository.
                findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("Workout Day not exist"));

        Exercise exercise = exerciseRepository.findByIdAndWorkoutDayId(exerciseID, workoutDay.getId())
                .orElseThrow(() -> new DataNotExist("Exercise not Exist"));


        exerciseRepository.deleteById(exercise.getId());

    }

    @Override
    public ExerciseResponse editExercise(String username, Long workoutPlanId,
                                         Long workoutDayId, Long exerciseID,
                                         ExerciseRequest request) {

        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout plan not exist"));

        WorkoutDay workoutDay = workoutDayRepository.
                findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("Workout Day not exist"));

        Exercise e = exerciseRepository.findByIdAndWorkoutDayId(exerciseID, workoutDay.getId())
                .orElseThrow(() -> new DataNotExist("Exercise not Exist"));

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

        return new ExerciseResponse(exerciseRepository.save(e));

    }

}
