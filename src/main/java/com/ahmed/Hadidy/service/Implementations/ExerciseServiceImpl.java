package com.ahmed.Hadidy.service.Implementations;


import com.ahmed.Hadidy.entity.Exercise;
import com.ahmed.Hadidy.repository.ExerciseRepository;
import com.ahmed.Hadidy.service.interfaces.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findAll() {

        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(Long id) {

        return Optional.of(exerciseRepository.findById(id).orElseThrow(()->new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {

        exerciseRepository.deleteById(id);

    }

    @Override
    public Exercise update(Long id, Exercise exercise) {

        Exercise e = exerciseRepository.findById(id).orElseThrow(()-> new RuntimeException("not found"));

        e.setDescription(exercise.getDescription());
        e.setName(exercise.getName());
        e.setPicture(exercise.getPicture());
        e.setSets(exercise.getSets());
        e.setRepeat(exercise.getRepeat());

        return exerciseRepository.save(e);
    }


}
