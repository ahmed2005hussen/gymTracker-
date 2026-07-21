package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.repository.DietPlanRepository;
import com.ahmed.Hadidy.service.interfaces.DietPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietPlanServiceImpl implements DietPlanService {


    private final DietPlanRepository dietPlanRepository;


    public DietPlanServiceImpl(DietPlanRepository dietPlanRepository) {
        this.dietPlanRepository = dietPlanRepository;
    }

    @Override
    public DietPlan save(DietPlan dietPlan) {

        return dietPlanRepository.save(dietPlan);
    }

    @Override
    public List<DietPlan> findAll() {

        return dietPlanRepository.findAll();

    }

    @Override
    public Optional<DietPlan> findById(Long id) {

        return Optional.of(dietPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found")));

    }

    @Override
    public void deleteById(Long id) {

        dietPlanRepository.deleteById(id);
    }

    @Override
    public DietPlan update(Long id, DietPlan dietPlan) {

        DietPlan d = dietPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        d.setDescription(dietPlan.getDescription());
        d.setTitle(dietPlan.getTitle());

        return dietPlanRepository.save(d);

    }

    @Override
    public List<DietPlan> findByUserId(Long userId) {

        return dietPlanRepository.findByProfileId(userId);

    }
}
