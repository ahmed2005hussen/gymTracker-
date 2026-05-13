package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
}
