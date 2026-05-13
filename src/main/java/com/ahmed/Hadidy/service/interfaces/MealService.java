package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.entity.Meal;

import java.util.List;
import java.util.Optional;

public interface MealService {

    Meal save(Meal meal);

    List<Meal> findAll();

    Optional<Meal> findById(Long id);

    void deleteById(Long id );

    Meal update(Long id, Meal meal);


}
