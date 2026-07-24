package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.response.DietPlanResponse;
import com.ahmed.Hadidy.dto.request.CreateDietPlanRequest;
import com.ahmed.Hadidy.dto.request.DietPlanRequest;
import com.ahmed.Hadidy.entity.DietPlan;
import com.ahmed.Hadidy.entity.Profile;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.DietPlanRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.support.TestResultLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, TestResultLogger.class})
class DietPlanServiceImplTest {

    private static final Long PROFILE_ID = 200L;

    @Mock
    private DietPlanRepository dietPlanRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DietPlanServiceImpl dietPlanService;

    private Profile profile;

    @BeforeEach
    void setUp() {
        User user = new User("ahmed", "encoded-password");
        profile = new Profile();
        profile.setId(PROFILE_ID);
        profile.setUser(user);
        user.setProfile(profile);
        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.of(user));
    }

    @Test
    void createDietPlan_assignsTheAuthenticatedUsersProfile() {
        CreateDietPlanRequest request = new CreateDietPlanRequest();
        request.setTitle("Cutting plan");
        request.setDescription("High protein meals");
        when(dietPlanRepository.save(any(DietPlan.class))).thenAnswer(invocation -> {
            DietPlan plan = invocation.getArgument(0);
            plan.setId(10L);
            return plan;
        });

        DietPlanResponse response = dietPlanService.createDietPlan("ahmed", request);

        ArgumentCaptor<DietPlan> captor = ArgumentCaptor.forClass(DietPlan.class);
        verify(dietPlanRepository).save(captor.capture());
        assertSame(profile, captor.getValue().getProfile());
        assertEquals(10L, response.getId());
        assertEquals("Cutting plan", response.getTitle());
    }

    @Test
    void listDietPlan_queriesUsingProfileIdNotUserId() {
        when(dietPlanRepository.findAllByProfileId(PROFILE_ID))
                .thenReturn(List.of(dietPlan(10L, "Cutting plan", "High protein meals")));

        List<DietPlanResponse> response = dietPlanService.listDietPlan("ahmed");

        verify(dietPlanRepository).findAllByProfileId(PROFILE_ID);
        assertEquals(1, response.size());
        assertEquals("Cutting plan", response.getFirst().getTitle());
    }

    @Test
    void editDietPlan_preservesFieldsOmittedFromPatch() {
        DietPlan plan = dietPlan(10L, "Old plan", "Existing description");
        when(dietPlanRepository.findByIdAndProfileId(10L, PROFILE_ID)).thenReturn(Optional.of(plan));
        when(dietPlanRepository.save(plan)).thenReturn(plan);
        DietPlanRequest request = new DietPlanRequest();
        request.setDescription("Updated description");

        DietPlanResponse response = dietPlanService.editDietPlan("ahmed", 10L, request);

        assertEquals("Old plan", response.getTitle());
        assertEquals("Updated description", response.getDescription());
        verify(dietPlanRepository).findByIdAndProfileId(10L, PROFILE_ID);
    }

    @Test
    void getDietPlan_usesProfileIdToEnforceOwnership() {
        when(dietPlanRepository.findByIdAndProfileId(10L, PROFILE_ID))
                .thenReturn(Optional.of(dietPlan(10L, "Cutting plan", "High protein meals")));

        DietPlanResponse response = dietPlanService.getDietPlan("ahmed", 10L);

        assertEquals("Cutting plan", response.getTitle());
        verify(dietPlanRepository).findByIdAndProfileId(10L, PROFILE_ID);
    }

    @Test
    void deleteDietPlan_deletesOnlyAnOwnedDietPlan() {
        when(dietPlanRepository.findByIdAndProfileId(10L, PROFILE_ID))
                .thenReturn(Optional.of(dietPlan(10L, "Cutting plan", "High protein meals")));

        dietPlanService.deleteDietPlan("ahmed", 10L);

        verify(dietPlanRepository).findByIdAndProfileId(10L, PROFILE_ID);
        verify(dietPlanRepository).deleteById(10L);
    }

    private DietPlan dietPlan(Long id, String title, String description) {
        DietPlan plan = new DietPlan();
        plan.setId(id);
        plan.setProfile(profile);
        plan.setTitle(title);
        plan.setDescription(description);
        return plan;
    }
}
