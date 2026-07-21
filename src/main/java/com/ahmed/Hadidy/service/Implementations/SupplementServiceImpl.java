package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.Supplement;
import com.ahmed.Hadidy.repository.SupplementRepository;
import com.ahmed.Hadidy.service.interfaces.SupplementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplementServiceImpl implements SupplementService {

    private final SupplementRepository supplementRepository;

    public SupplementServiceImpl(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    @Override
    public Supplement save(Supplement supplement) {
        return supplementRepository.save(supplement);
    }

    @Override
    public List<Supplement> findAll() {
        return supplementRepository.findAll();
    }

    @Override
    public Optional<Supplement> findById(Long id) {
        return Optional.of(supplementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        supplementRepository.deleteById(id);
    }

    @Override
    public Supplement update(Long id, Supplement supplement) {
        Supplement s = supplementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        s.setName(supplement.getName());
        s.setDescription(supplement.getDescription());
        s.setPrice(supplement.getPrice());
        s.setPicture(supplement.getPicture());

        return supplementRepository.save(s);
    }

    @Override
    public List<Supplement> findByUserId(Long userId) {
        return supplementRepository.findByProfileId(userId);
    }
}