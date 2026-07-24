package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.reponse.ProfileResponse;
import com.ahmed.Hadidy.dto.request.ProfileRequest;
import com.ahmed.Hadidy.entity.Profile;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.ProfileRepository;
import com.ahmed.Hadidy.service.interfaces.UserService;
import com.ahmed.Hadidy.support.TestResultLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, TestResultLogger.class})
class ProfileServiceImplementationTest {

    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProfileServiceImplementation profileService;

    private Profile profile;

    @BeforeEach
    void setUp() {
        User user = new User("ahmed", "encoded-password");
        profile = new Profile();
        profile.setId(20L);
        profile.setFullName("Ahmed");
        profile.setWeight(70.0);
        profile.setHeight(1.75);
        profile.setStartSubscribe(LocalDate.of(2026, 1, 1));
        profile.setEndSubscribe(LocalDate.of(2026, 12, 31));
        profile.setUser(user);
        user.setProfile(profile);

        when(userService.findByUsername("ahmed")).thenReturn(Optional.of(user));
    }

    @Test
    void editProfile_updatesOnlyProvidedFieldsAndRecalculatesBmi() {
        ProfileRequest request = new ProfileRequest();
        request.setWeight(80.0);
        request.setFullName("Ahmed Hussein");
        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProfileResponse response = profileService.editProfile(request, "ahmed");

        assertEquals("Ahmed Hussein", response.getFullName());
        assertEquals(80.0, response.getWeight());
        assertEquals(1.75, response.getHeight());
        assertEquals(26.122448979591837, response.getBmi(), 0.000001);
        verify(profileRepository).save(profile);
    }

    @Test
    void editProfile_rejectsSubscriptionEndBeforeStart() {
        ProfileRequest request = new ProfileRequest();
        request.setEndSubscribe(LocalDate.of(2025, 12, 31));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> profileService.editProfile(request, "ahmed"));

        assertEquals("Subscription end date should be after start date", exception.getMessage());
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    void getProfile_returnsMappedProfileForAuthenticatedUser() {
        ProfileResponse response = profileService.getProfile("ahmed");

        assertEquals(20L, response.getId());
        assertEquals("Ahmed", response.getFullName());
    }
}
