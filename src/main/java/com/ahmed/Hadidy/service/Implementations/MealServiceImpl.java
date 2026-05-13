package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.entity.Meal;
import com.ahmed.Hadidy.repository.MealRepository;
import com.ahmed.Hadidy.service.interfaces.MealService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @Override
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    @Override
    public Optional<Meal> findById(Long id) {

        return Optional.of(mealRepository.findById(id).orElseThrow(()->new RuntimeException("not fuond")));
    }

    @Override
    public void deleteById(Long id) {

        mealRepository.deleteById(id);

    }

    @Override
    public Meal update(Long id, Meal meal) {

        Meal m = mealRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        m.setCalories(meal.getCalories());
        m.setName(meal.getName());
        m.setCarbs(meal.getCarbs());
        m.setFats(meal.getFats());
        m.setPhoto(meal.getPhoto());
        m.setProtein(meal.getProtein());
        m.setRecipe(meal.getRecipe());
        m.setTime(meal.getTime());

        return mealRepository.save(m);
    }

}
