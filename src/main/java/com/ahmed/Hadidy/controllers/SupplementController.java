package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.SupplementResponse;
import com.ahmed.Hadidy.entity.Supplement;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.SupplementRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/supplement")
@RequiredArgsConstructor
public class SupplementController {

    private final SupplementRepository supplementRepository;
    final private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<?> createSupplement(@RequestBody SupplementResponse Dto
            , Authentication authentication) {

        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            Supplement supplement = new Supplement();

            supplement.setPicture(Dto.getPicture());
            supplement.setProfile(user.getProfile());
            supplement.setName(Dto.getName());
            supplement.setDescription(Dto.getDescription());
            supplement.setPrice(Dto.getPrice());

            Supplement saved = supplementRepository.save(supplement);

            if (saved.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        "Supplement is created"
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");

        }

    }


    @GetMapping("/list")
    public ResponseEntity<?> listSupplement(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<Supplement> supplements = supplementRepository.findByProfileId(user.getId());

            if (supplements != null) {

                List<SupplementResponse> supplementResponses = new ArrayList<>();

                for (Supplement s : supplements) {

                    SupplementResponse supplement = new SupplementResponse();


                    supplement.setPicture(s.getPicture());
                    supplement.setName(s.getName());
                    supplement.setDescription(s.getDescription());
                    supplement.setPrice(s.getPrice());

                    supplementResponses.add(supplement);
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        supplementResponses
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Supplements "
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }


    @GetMapping("list/{id}")
    public ResponseEntity<?> getSupplement
            (Authentication authentication, @PathVariable Long id) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<Supplement> supplements = supplementRepository.findByProfileId(user.getId());

            if (supplements != null) {


                for (Supplement s : supplements) {

                    if (s.getId() == id) {
                        SupplementResponse supplementResponse = new SupplementResponse();

                        supplementResponse.setPrice(s.getPrice());
                        supplementResponse.setPicture(s.getPicture());
                        supplementResponse.setName(s.getName());
                        supplementResponse.setDescription(s.getDescription());
                        return ResponseEntity.status(HttpStatus.OK).body(
                                supplementResponse
                        );
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(
                        "Not Found"
                );


            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        "don't have Supplement"
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }

    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteSupplement
            (@PathVariable Long id , Authentication authentication){


        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not Found")
            );

            List<Supplement> supplements = supplementRepository.findByProfileId(user.getId());

            for (Supplement s : supplements) {

                if(s.getId() == id) {
                    supplementRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).body(
                            "was Deleted"
                    );
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Not Found"
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "Error"
            );
        }


    }


    @PatchMapping("/edit/{id}")
    ResponseEntity<String> editSupplement
            (@PathVariable Long id,@RequestBody SupplementResponse request,
                                           Authentication authentication
    ) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not found")
            );

            Supplement supplement = supplementRepository.findById(id).orElseThrow(()
                    -> new RuntimeException("not found"));

            if(supplement.getProfile().getId() != user.getId()){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Not found"
                );
            }

            if (request.getDescription() != null) {
                supplement.setDescription(request.getDescription());
            }
            if (request.getName() != null) {
                supplement.setName(request.getName());
            }
            if (request.getPicture() != null) {
                supplement.setPicture(request.getPicture());
            }
            if (request.getPrice() != null) {
                supplement.setPrice(request.getPrice());
            }
            supplementRepository.save(supplement);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "supplement is Edited"
            );
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }
    }


}
