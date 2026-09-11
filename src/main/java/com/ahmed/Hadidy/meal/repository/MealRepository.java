package com.ahmed.Hadidy.meal.repository;

import com.ahmed.Hadidy.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal,Long> {
    List<Meal> findByDietPlanId(Long id);
    Optional<Meal> findByIdAndDietPlanId(Long id , Long dietPlanId);
}
