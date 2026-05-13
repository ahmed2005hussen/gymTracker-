package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {


    Profile save(Profile profile);

    List<Profile> findAll();

    Optional<Profile> findById(Long id);

    void deleteById(Long id );

    Profile update(Long id, Profile profile);


}
