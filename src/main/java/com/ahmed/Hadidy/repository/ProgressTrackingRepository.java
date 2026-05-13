package com.ahmed.Hadidy.repository;


import com.ahmed.Hadidy.entity.ProgressTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTracking,Long> {

    List<ProgressTracking> findByUserId(Long userId);
}
