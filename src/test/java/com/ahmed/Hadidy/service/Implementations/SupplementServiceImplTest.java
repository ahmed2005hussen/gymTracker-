package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.response.SupplementResponse;
import com.ahmed.Hadidy.dto.request.CreateSupplementRequest;
import com.ahmed.Hadidy.dto.request.SupplementRequest;
import com.ahmed.Hadidy.entity.Profile;
import com.ahmed.Hadidy.entity.Supplement;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.SupplementRepository;
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
class SupplementServiceImplTest {

    private static final Long PROFILE_ID = 200L;

    @Mock
    private SupplementRepository supplementRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private SupplementServiceImpl supplementService;

    private Profile profile;

    @BeforeEach
    void setUp() {
        User user = new User("ahmed", "encoded-password");
        user.setId(100L);
        profile = new Profile();
        profile.setId(PROFILE_ID);
        profile.setUser(user);
        user.setProfile(profile);
        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.of(user));
    }

    @Test
    void createSupplement_assignsTheAuthenticatedUsersProfile() {
        CreateSupplementRequest request = new CreateSupplementRequest();
        request.setName("Creatine");
        request.setPrice(450.0);
        when(supplementRepository.save(any(Supplement.class))).thenAnswer(invocation -> {
            Supplement supplement = invocation.getArgument(0);
            supplement.setId(10L);
            return supplement;
        });

        SupplementResponse response = supplementService.createSupplement(request, "ahmed");

        ArgumentCaptor<Supplement> captor = ArgumentCaptor.forClass(Supplement.class);
        verify(supplementRepository).save(captor.capture());
        assertSame(profile, captor.getValue().getProfile());
        assertEquals(10L, response.getId());
        assertEquals(450.0, response.getPrice());
    }

    @Test
    void listSupplement_queriesUsingProfileIdNotUserId() {
        Supplement supplement = supplement(10L, "Creatine", 450.0);
        when(supplementRepository.findAllByProfileId(PROFILE_ID)).thenReturn(List.of(supplement));

        List<SupplementResponse> response = supplementService.listSupplement("ahmed");

        verify(supplementRepository).findAllByProfileId(PROFILE_ID);
        assertEquals(1, response.size());
        assertEquals("Creatine", response.getFirst().getName());
    }

    @Test
    void editSupplement_preservesFieldsOmittedFromPatch() {
        Supplement supplement = supplement(10L, "Old name", 450.0);
        supplement.setDescription("Existing description");
        when(supplementRepository.findByIdAndProfileId(10L, PROFILE_ID)).thenReturn(Optional.of(supplement));
        when(supplementRepository.save(supplement)).thenReturn(supplement);
        SupplementRequest request = new SupplementRequest();
        request.setPrice(500.0);

        SupplementResponse response = supplementService.editSupplement(10L, request, "ahmed");

        assertEquals("Old name", response.getName());
        assertEquals("Existing description", response.getDescription());
        assertEquals(500.0, response.getPrice());
        verify(supplementRepository).findByIdAndProfileId(10L, PROFILE_ID);
    }

    @Test
    void getSupplement_usesProfileIdToEnforceOwnership() {
        Supplement supplement = supplement(10L, "Creatine", 450.0);
        when(supplementRepository.findByIdAndProfileId(10L, PROFILE_ID)).thenReturn(Optional.of(supplement));

        SupplementResponse response = supplementService.getSupplement(10L, "ahmed");

        assertEquals("Creatine", response.getName());
        verify(supplementRepository).findByIdAndProfileId(10L, PROFILE_ID);
    }

    @Test
    void deleteSupplement_deletesOnlyAnOwnedSupplement() {
        Supplement supplement = supplement(10L, "Creatine", 450.0);
        when(supplementRepository.findByIdAndProfileId(10L, PROFILE_ID)).thenReturn(Optional.of(supplement));

        supplementService.deleteSupplement(10L, "ahmed");

        verify(supplementRepository).findByIdAndProfileId(10L, PROFILE_ID);
        verify(supplementRepository).deleteById(10L);
    }

    private Supplement supplement(Long id, String name, double price) {
        Supplement supplement = new Supplement();
        supplement.setId(id);
        supplement.setProfile(profile);
        supplement.setName(name);
        supplement.setPrice(price);
        return supplement;
    }
}
