package com.ahmed.Hadidy.diet.service.impl;

import com.ahmed.Hadidy.diet.dto.DietPlanResponse;
import com.ahmed.Hadidy.diet.dto.CreateDietPlanRequest;
import com.ahmed.Hadidy.diet.dto.DietPlanRequest;
import com.ahmed.Hadidy.diet.entity.DietPlan;
import com.ahmed.Hadidy.user.entity.HadidyUser;
import com.ahmed.Hadidy.exception.DataNotExist;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.diet.repository.DietPlanRepository;
import com.ahmed.Hadidy.user.repository.UserRepository;
import com.ahmed.Hadidy.diet.service.DietPlanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietPlanServiceImpl implements DietPlanService {

    private final DietPlanRepository dietPlanRepository;
    private final UserRepository userRepository;

    private HadidyUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    @Transactional
    public DietPlanResponse createDietPlan(String username, CreateDietPlanRequest request) {

        HadidyUser user = findByUsername(username);
        DietPlan dietPlan = new DietPlan();

        dietPlan.setTitle(request.getTitle());
        dietPlan.setProfile(user.getProfile());
        dietPlan.setDescription(request.getDescription());

        DietPlan saved = dietPlanRepository.save(dietPlan);

        return new DietPlanResponse(saved);

    }

    @Override
    public List<DietPlanResponse> listDietPlan(String username) {
        HadidyUser user = findByUsername(username);

        List<DietPlan> dietPlans = dietPlanRepository
                .findAllByProfileId(user.getProfile().getId());

        return dietPlans.stream().map(DietPlanResponse::new).toList();

    }

    @Override
    public DietPlanResponse getDietPlan(String username, Long id) {
        HadidyUser user = findByUsername(username);

        DietPlan d = dietPlanRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet plan does not exist"));
        return new DietPlanResponse(d);

    }

    @Override
    public DietPlanResponse editDietPlan(String username, Long id,
                                         DietPlanRequest request) {
        HadidyUser user = findByUsername(username);
        DietPlan d = dietPlanRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet plan does not exist"));

        if(request.getDescription() != null){
            d.setDescription(request.getDescription());
        }

        if(request.getTitle() != null){
            d.setTitle(request.getTitle());
        }

        return new DietPlanResponse(dietPlanRepository.save(d));


    }

    @Override
    public void deleteDietPlan(String username, Long id) {
        HadidyUser user = findByUsername(username);
        DietPlan d = dietPlanRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Diet plan does not exist"));

        dietPlanRepository.deleteById(d.getId());
    }
}
