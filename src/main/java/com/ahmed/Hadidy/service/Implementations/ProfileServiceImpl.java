package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.Profile;
import com.ahmed.Hadidy.repository.ProfileRepository;
import com.ahmed.Hadidy.service.interfaces.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return Optional.of(profileRepository.findById(id).orElseThrow(()->new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile update(Long id, Profile profile) {

        Profile p = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        p.setEndSubscribe(profile.getEndSubscribe());
        p.setPublic(profile.isPublic());
        p.setGoal(profile.getGoal());
        p.setHeight(profile.getHeight());
        p.setFullName(profile.getFullName());
        p.setGymPrice(profile.getGymPrice());
        p.setStartSubscribe(profile.getStartSubscribe());
        p.setWeight(profile.getWeight());
        p.setProfilePicture(profile.getProfilePicture());
        return profileRepository.save(p);
    }
}
