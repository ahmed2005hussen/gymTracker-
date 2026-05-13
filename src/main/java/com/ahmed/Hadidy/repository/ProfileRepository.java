package com.ahmed.Hadidy.repository;


import com.ahmed.Hadidy.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
