package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.request.CreateMealRequest;
import com.ahmed.Hadidy.dto.request.MealRequest;
import com.ahmed.Hadidy.dto.response.MealResponse;
import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.entity.Meal;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.exceptions.DataNotExist;
import com.ahmed.Hadidy.exceptions.UserNotFoundException;
import com.ahmed.Hadidy.repository.DietPlanRepository;
import com.ahmed.Hadidy.repository.MealRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.service.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DietPlanRepository dietPlanRepository;

    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }


    @Override
    @Transactional
    public MealResponse createMeal(String username,
                                   Long dietPlanId, CreateMealRequest request) {
        User user = findByUsername(username);

        DietPlan dietPlan = dietPlanRepository.
                findByIdAndProfileId(dietPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet Plan does not exist"));


        Meal meal = new Meal();

        meal.setDietPlan(dietPlan);
        meal.setRecipe(request.getRecipe());
        meal.setPhoto(request.getPhoto());
        meal.setName(request.getName());
        meal.setCalories(request.getCalories());
        meal.setCarbs(request.getCarbs());
        meal.setProtein(request.getProtein());
        meal.setFats(request.getFats());
        meal.setTime(request.getTime());

        return new MealResponse(mealRepository.save(meal));

    }

    @Override
    public List<MealResponse> listMeal(String username, Long dietPlanId) {
        User user = findByUsername(username);

        DietPlan dietPlan = dietPlanRepository.
                findByIdAndProfileId(dietPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet Plan does not exist"));

        return mealRepository.findByDietPlanId(dietPlan.getId())
                .stream().map(MealResponse::new).toList();

    }

    @Override
    public MealResponse getMeal(String username, Long dietPlanId, Long mealId) {
        User user = findByUsername(username);

        DietPlan dietPlan = dietPlanRepository.
                findByIdAndProfileId(dietPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet Plan does not exist"));

        Meal m = mealRepository.findByIdAndDietPlanId(mealId, dietPlan.getId())
                .orElseThrow(() -> new DataNotExist("This meal does not exist")
                );

        return new MealResponse(m);
    }

    @Override
    @Transactional
    public void deleteMeal(String username, Long dietPlanId, Long mealId) {
        User user = findByUsername(username);

        DietPlan dietPlan = dietPlanRepository.
                findByIdAndProfileId(dietPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet Plan does not exist"));

        Meal m = mealRepository.findByIdAndDietPlanId(mealId, dietPlan.getId())
                .orElseThrow(() -> new DataNotExist("This meal does not exist")
                );

        mealRepository.deleteById(m.getId());
    }

    @Override
    @Transactional
    public MealResponse editMeal(String username, Long dietPlanId, Long mealId, MealRequest request) {
        User user = findByUsername(username);

        DietPlan dietPlan = dietPlanRepository.
                findByIdAndProfileId(dietPlanId, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet Plan does not exist"));

        Meal m = mealRepository.findByIdAndDietPlanId(mealId, dietPlan.getId())
                .orElseThrow(() -> new DataNotExist("This meal does not exist")
                );

        if (request.getRecipe() != null) {
            m.setRecipe(request.getRecipe());
        }
        if (request.getName() != null) {
            m.setName(request.getName());
        }
        if (request.getPhoto() != null) {
            m.setPhoto(request.getPhoto());
        }
        if (request.getCalories() != null) {
            m.setCalories(request.getCalories());
        }
        if (request.getProtein() != null) {
            m.setProtein(request.getProtein());
        }
        if (request.getCarbs() != null) {
            m.setCarbs(request.getCarbs());
        }
        if (request.getFats() != null) {
            m.setFats(request.getFats());
        }
        if (request.getTime() != null) {
            m.setTime(request.getTime());
        }
        return new MealResponse(mealRepository.save(m));
    }


}
