package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
