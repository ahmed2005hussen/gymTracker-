package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Long> {
    List<Meal> findByDietPlanId(Long id);
}
