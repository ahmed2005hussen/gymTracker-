package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplementRepository extends JpaRepository<Supplement,Long> {
    List<Supplement> findAllByProfileId(Long profileId);
    Optional<Supplement> findByIdAndProfileId(Long id , Long profileId);
}
