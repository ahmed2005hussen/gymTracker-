package com.ahmed.Hadidy.service.Implementations;

import com.ahmed.Hadidy.dto.response.SupplementResponse;
import com.ahmed.Hadidy.dto.request.CreateSupplementRequest;
import com.ahmed.Hadidy.dto.request.SupplementRequest;
import com.ahmed.Hadidy.entity.Supplement;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.exceptions.DataNotExist;
import com.ahmed.Hadidy.exceptions.UserNotFoundException;
import com.ahmed.Hadidy.repository.SupplementRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import com.ahmed.Hadidy.service.interfaces.SupplementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplementServiceImpl implements SupplementService {

    private final SupplementRepository supplementRepository;
    private final UserRepository userRepository;


    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    @Override
    @Transactional
    public SupplementResponse createSupplement(CreateSupplementRequest request,
                                               String username) {

        User user = findByUsername(username);

        Supplement supplement = new Supplement();

        supplement.setProfile(user.getProfile());
        supplement.setName(request.getName());
        supplement.setDescription(request.getDescription());
        supplement.setPrice(request.getPrice());
        supplement.setPicture(request.getPicture());


        Supplement saved = supplementRepository.save(supplement);

        return new SupplementResponse(saved);

    }

    @Override
    public List<SupplementResponse> listSupplement(String username) {

        User user = findByUsername(username);

        List<Supplement> supplements = supplementRepository
                .findAllByProfileId(user.getProfile().getId());

        return supplements.stream()
                .map(SupplementResponse::new)
                .toList();
    }

    @Override
    public SupplementResponse getSupplement(Long id, String username) {

        User user = findByUsername(username);

        Supplement s = supplementRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("This supplement does not exist"));

        return new SupplementResponse(s);

    }

    @Override
    @Transactional
    public void deleteSupplement(Long id, String username) {
        User user = findByUsername(username);

        Supplement s = supplementRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Supplement does not exist"));
        supplementRepository.deleteById(s.getId());
    }

    @Override
    @Transactional
    public SupplementResponse editSupplement(Long id, SupplementRequest request, String username) {

        User user = findByUsername(username);

        Supplement s = supplementRepository.findByIdAndProfileId(id, user.getProfile().getId())
                .orElseThrow(() -> new DataNotExist("Supplement does not exist"));

        if (request.getDescription() != null) {
            s.setDescription(request.getDescription());
        }
        if (request.getName() != null) {
            s.setName(request.getName());
        }
        if (request.getPicture() != null) {
            s.setPicture(request.getPicture());
        }
        if (request.getPrice() != null) {
            s.setPrice(request.getPrice());
        }
        supplementRepository.save(s);

        return new SupplementResponse(s);
    }


}
