package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {


    Optional<Exercise> findByIdAndWorkoutDayId(Long id, Long workoutDayId);

    List<Exercise> findAllByWorkoutDayId(Long workoutDayId);


}
