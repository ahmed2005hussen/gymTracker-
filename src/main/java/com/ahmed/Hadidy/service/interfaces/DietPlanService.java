package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.entity.DietPlan;

import java.util.List;
import java.util.Optional;

public interface DietPlanService  {

    DietPlan save(DietPlan dietPlan);

    List<DietPlan> findAll();

    Optional<DietPlan> findById(Long id);

    void deleteById(Long id );

    DietPlan update(Long id, DietPlan dietPlan);

    List<DietPlan> findByUserId(Long userId);
}
