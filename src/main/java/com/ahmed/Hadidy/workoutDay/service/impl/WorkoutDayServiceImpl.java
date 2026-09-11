package com.ahmed.Hadidy.workoutDay.service.impl;


import com.ahmed.Hadidy.workoutDay.dto.CreateWorkoutDayRequest;
import com.ahmed.Hadidy.workoutDay.dto.WorkoutDayRequest;
import com.ahmed.Hadidy.workoutDay.dto.WorkoutDayResponse;
import com.ahmed.Hadidy.user.entity.HadidyUser;
import com.ahmed.Hadidy.workoutDay.entity.WorkoutDay;
import com.ahmed.Hadidy.workoutPlan.entity.WorkoutPlan;
import com.ahmed.Hadidy.exception.DataNotExist;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.workoutDay.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.workoutPlan.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.workoutDay.service.WorkoutDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutDayServiceImpl implements WorkoutDayService {

    private final WorkoutDayRepository workoutDayRepository;
    private final UserRepository userRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    private HadidyUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    public WorkoutDayResponse createWorkoutDay(String username, Long workoutPlanId,
                                               CreateWorkoutDayRequest request) {

        HadidyUser user = findByUsername(username);

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

        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        return workoutDayRepository.findAllByWorkoutPlanId(workoutPlan.getId())
                .stream().map(WorkoutDayResponse::new).toList();

    }

    @Override
    public WorkoutDayResponse getWorkoutDay(String username, Long workoutPlanId, Long workoutDayId) {

        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        WorkoutDay workoutDay = workoutDayRepository.findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("This workout day not exist"));

        return new WorkoutDayResponse(workoutDay);

    }

    @Override
    public void deleteWorkoutDay(String username, Long workoutPlanId, Long workoutDayId) {
        HadidyUser user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(workoutPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Workout Plan not found"));

        WorkoutDay workoutDay = workoutDayRepository.findByIdAndWorkoutPlanId(workoutDayId, workoutPlan.getId())
                .orElseThrow(() -> new DataNotExist("This workout day not exist"));

        workoutDayRepository.deleteById(workoutDay.getId()) ;

    }

    @Override
    public WorkoutDayResponse editWorkoutDay(String username, Long workoutPlanId, Long workoutDayId, WorkoutDayRequest request) {

        HadidyUser user = findByUsername(username);

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