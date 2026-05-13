package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.entity.ProgressTracking;
import com.ahmed.Hadidy.repository.ProgressTrackingRepository;
import com.ahmed.Hadidy.service.interfaces.ProgressTrackingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProgressTrackingServiceImpl implements ProgressTrackingService {

    private final ProgressTrackingRepository progressTrackingRepository;

    public ProgressTrackingServiceImpl(ProgressTrackingRepository progressTrackingRepository) {
        this.progressTrackingRepository = progressTrackingRepository;
    }

    @Override
    public ProgressTracking save(ProgressTracking progressTracking) {
        return progressTrackingRepository.save(progressTracking);
    }

    @Override
    public List<ProgressTracking> findAll() {
        return progressTrackingRepository.findAll();
    }

    @Override
    public Optional<ProgressTracking> findById(Long id) {
        return Optional.of(progressTrackingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public void deleteById(Long id) {
        progressTrackingRepository.deleteById(id);
    }

    @Override
    public ProgressTracking update(Long id, ProgressTracking progressTracking) {
        ProgressTracking p = progressTrackingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        p.setWeight(progressTracking.getWeight());
        p.setHeight(progressTracking.getHeight());
        p.setNote(progressTracking.getNote());
        p.setPhoto(progressTracking.getPhoto());
        p.setPublic(progressTracking.isPublic());
        p.setTakenDate(progressTracking.getTakenDate());

        return progressTrackingRepository.save(p);
    }

    @Override
    public List<ProgressTracking> findByUserId(Long userId) {
        return progressTrackingRepository.findByUserId(userId);
    }
}
