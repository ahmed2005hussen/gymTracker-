package com.ahmed.Hadidy.service.Implementations;


import com.ahmed.Hadidy.dto.request.CreateWorkoutDayRequest;
import com.ahmed.Hadidy.dto.request.WorkoutDayRequest;
import com.ahmed.Hadidy.dto.response.WorkoutDayResponse;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.exceptions.DataNotExist;
import com.ahmed.Hadidy.exceptions.UserNotFoundException;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.service.interfaces.WorkoutDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutDayServiceImpl implements WorkoutDayService {

    private final WorkoutDayRepository workoutDayRepository;
    private final UserRepository userRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    public WorkoutDayResponse createWorkoutDay(String username, Long workoutPlanId,
                                               CreateWorkoutDayRequest request) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));


        WorkoutDay workoutDay = new WorkoutDay();

        workoutDay.setWorkoutPlan(workoutPlan);

        workoutDay.setName(request.getName());
        workoutDay.setDescription(request.getDescription());
        workoutDay.setExpectedTime(request.getExpectedTime());
        workoutDay.setImage(request.getImage());
        workoutDay.setTotalExercises(request.getTotalExercises());
        workoutDay.setTotalRepeat(request.getTotalRepeat());

        return new WorkoutDayResponse(workoutDayRepository.save(workoutDay));

    }

    @Override
    public List<WorkoutDayResponse> listWorkoutDay(String username, Long workoutPlanId) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        return workoutDayRepository.findAllByWorkoutPlanId(workoutPlan.getId())
                .stream().map(WorkoutDayResponse::new).toList();

    }

    @Override
    public WorkoutDayResponse getWorkoutDay(String username, Long workoutPlanId, Long workoutDayId) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        WorkoutDay workoutDay = workoutDayRepository.findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("This workout day not exist"));

        return new WorkoutDayResponse(workoutDay);

    }

    @Override
    public void deleteWorkoutDay(String username, Long workoutPlanId, Long workoutDayId) {
        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        WorkoutDay workoutDay = workoutDayRepository.findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("This workout day not exist"));

        workoutDayRepository.deleteById(workoutDay.getId()) ;

    }

    @Override
    public WorkoutDayResponse editWorkoutDay(String username, Long workoutPlanId, Long workoutDayId, WorkoutDayRequest request) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        WorkoutDay w = workoutDayRepository.findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("This workout day not exist"));


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
        return new WorkoutDayResponse(workoutDayRepository.save(w));

    }
}