package com.ahmed.Hadidy.repository;

import com.ahmed.Hadidy.entity.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplementRepository extends JpaRepository<Supplement,Long> {
    List<Supplement> findByUserId(Long userId);

}
