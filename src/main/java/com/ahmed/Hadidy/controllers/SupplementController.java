package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.response.SupplementResponse;
import com.ahmed.Hadidy.dto.request.CreateSupplementRequest;
import com.ahmed.Hadidy.dto.request.SupplementRequest;
import com.ahmed.Hadidy.service.interfaces.SupplementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final SupplementService supplementService;

    @PostMapping
    public ResponseEntity<SupplementResponse> createSupplement(@RequestBody @Valid CreateSupplementRequest request
            , Authentication authentication) {

        SupplementResponse response =
                supplementService.createSupplement(request, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<SupplementResponse>> listSupplement(Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                supplementService.listSupplement(authentication.getName())
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplementResponse> getSupplement
            (Authentication authentication, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(supplementService.getSupplement(id, authentication.getName()));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplement
            (@PathVariable Long id, Authentication authentication) {


        supplementService.deleteSupplement(id, authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(
                "Supplement is Deleted"
        );

    }


    @PatchMapping("/{id}")
    public ResponseEntity<SupplementResponse> editSupplement
            (@PathVariable Long id, @Valid @RequestBody SupplementRequest request,
             Authentication authentication
            ) {

        return ResponseEntity.status(HttpStatus.OK).body(
                supplementService.editSupplement(id, request, authentication.getName())
        );

    }


}
