package com.ahmed.Hadidy.workoutPlan.service.impl;


import com.ahmed.Hadidy.workoutPlan.dto.CreateWorkoutPlanRequest;
import com.ahmed.Hadidy.workoutPlan.dto.WorkoutPlanRequest;
import com.ahmed.Hadidy.workoutPlan.dto.WorkoutPlanResponse;
import com.ahmed.Hadidy.user.entity.User;
import com.ahmed.Hadidy.workoutPlan.entity.WorkoutPlan;
import com.ahmed.Hadidy.exception.DataNotExist;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.workoutPlan.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.workoutPlan.service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final UserRepository userRepository;


    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }


    @Override
    @Transactional
    public WorkoutPlanResponse createWorkoutPlan(String username,
                                                 CreateWorkoutPlanRequest request) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = new WorkoutPlan();

        workoutPlan.setProfile(user.getProfile());
        workoutPlan.setName(request.getName());
        workoutPlan.setDescription(request.getDescription());
        workoutPlan.setPicture(request.getPicture());

        return new WorkoutPlanResponse(workoutPlanRepository.save(workoutPlan));

    }

    @Override
    public List<WorkoutPlanResponse> listWorkoutPlan(String username) {
        User user = findByUsername(username);

        List<WorkoutPlan> workoutPlans = workoutPlanRepository
                .findAllByProfileId(user.getProfile().getId());

        return workoutPlans.stream().map(WorkoutPlanResponse::new).toList();

    }

    @Override
    public WorkoutPlanResponse getWorkoutPlan(String username, Long id) {
        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("This Workout plan not found"));

        return new WorkoutPlanResponse(workoutPlan);
    }

    @Override
    @Transactional
    public void deleteWorkoutPlan(String username, Long id) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("This Workout plan not found"));

        workoutPlanRepository.deleteById(workoutPlan.getId());

    }

    @Override
    public WorkoutPlanResponse editWorkoutPlan(String username,
                                               Long id, WorkoutPlanRequest request) {

        User user = findByUsername(username);

        WorkoutPlan workoutPlan = workoutPlanRepository.
                findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("This Workout plan not found"));

        if (request.getName() != null) {
            workoutPlan.setName(request.getName());
        }
        if (request.getDescription() != null) {
            workoutPlan.setDescription(request.getDescription());
        }
        if (request.getPicture() != null) {
            workoutPlan.setPicture(request.getPicture());
        }
        return new WorkoutPlanResponse(workoutPlanRepository.save(workoutPlan));

    }

}
