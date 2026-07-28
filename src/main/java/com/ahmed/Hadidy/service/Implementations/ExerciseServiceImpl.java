package com.ahmed.Hadidy.service.Implementations;


import com.ahmed.Hadidy.dto.request.CreateExerciseRequest;
import com.ahmed.Hadidy.dto.request.ExerciseRequest;
import com.ahmed.Hadidy.dto.response.ExerciseResponse;
import com.ahmed.Hadidy.entity.Exercise;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.exceptions.DataNotExist;
import com.ahmed.Hadidy.exceptions.UserNotFoundException;
import com.ahmed.Hadidy.repository.ExerciseRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.service.interfaces.ExerciseService;
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


    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    public ExerciseResponse createExercise(String username,
                                           Long workoutPlanId,
                                           Long workoutDayId,
                                           CreateExerciseRequest request) {
        User user = findByUsername(username);

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

        User user = findByUsername(username);

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

        User user = findByUsername(username);

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


        User user = findByUsername(username);

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

        User user = findByUsername(username);

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
