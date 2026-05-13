package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.entity.Supplement;

import java.util.List;
import java.util.Optional;

public interface SupplementService {
    Supplement save(Supplement supplement);

    List<Supplement> findAll();

    Optional<Supplement> findById(Long id);

    void deleteById(Long id );

    Supplement update(Long id, Supplement supplement);

    List<Supplement> findByUserId(Long userId);

}
