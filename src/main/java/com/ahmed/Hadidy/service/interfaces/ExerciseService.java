package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.entity.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    Exercise save(Exercise exercise);

    List<Exercise> findAll();

    Optional<Exercise> findById(Long id);

    void deleteById(Long id );

    Exercise update(Long id, Exercise exercise);

}
