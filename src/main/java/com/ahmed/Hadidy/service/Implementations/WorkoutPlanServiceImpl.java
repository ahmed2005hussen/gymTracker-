package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.WorkoutPlan;
import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
import com.ahmed.Hadidy.service.interfaces.WorkoutPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public WorkoutPlan save(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> findAll() {
        return workoutPlanRepository.findAll();
    }

    @Override
    public Optional<WorkoutPlan> findById(Long id) {
        return Optional.of(workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        workoutPlanRepository.deleteById(id);
    }

    @Override
    public WorkoutPlan update(Long id, WorkoutPlan workoutPlan) {
        WorkoutPlan w = workoutPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        w.setName(workoutPlan.getName());
        w.setDescription(workoutPlan.getDescription());
        w.setPublic(workoutPlan.isPublic());
        w.setGenerateDate(workoutPlan.getGenerateDate());

        return workoutPlanRepository.save(w);
    }

    @Override
    public List<WorkoutPlan> findByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }
}