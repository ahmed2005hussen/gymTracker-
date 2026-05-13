package com.ahmed.Hadidy.service.Implementations;


import com.ahmed.Hadidy.entity.WorkoutDay;
import com.ahmed.Hadidy.repository.WorkoutDayRepository;
import com.ahmed.Hadidy.service.interfaces.WorkoutDayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WorkoutDayServiceImpl implements WorkoutDayService {

    private final WorkoutDayRepository workoutDayRepository;

    public WorkoutDayServiceImpl(WorkoutDayRepository workoutDayRepository) {
        this.workoutDayRepository = workoutDayRepository;
    }

    @Override
    public WorkoutDay save(WorkoutDay workoutDay) {
        return workoutDayRepository.save(workoutDay);
    }

    @Override
    public List<WorkoutDay> findAll() {
        return workoutDayRepository.findAll();
    }

    @Override
    public Optional<WorkoutDay> findById(Long id) {
        return Optional.of(workoutDayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        workoutDayRepository.deleteById(id);
    }

    @Override
    public WorkoutDay update(Long id, WorkoutDay workoutDay) {
        WorkoutDay w = workoutDayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        w.setName(workoutDay.getName());
        w.setDescription(workoutDay.getDescription());
        w.setExpectedTime(workoutDay.getExpectedTime());
        w.setImage(workoutDay.getImage());

        return workoutDayRepository.save(w);
    }

    @Override
    public List<WorkoutDay> findByWorkoutPlanId(Long workoutPlanId) {
        return workoutDayRepository.findByWorkoutPlanId(workoutPlanId);
    }
}