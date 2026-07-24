package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.reponse.SupplementResponse;
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
@RequestMapping("/api/supplement")
@RequiredArgsConstructor
public class SupplementController {

    private final SupplementService supplementService;

    @PostMapping("/create")
    public ResponseEntity<SupplementResponse> createSupplement(@RequestBody @Valid CreateSupplementRequest request
            , Authentication authentication) {

        SupplementResponse response =
                supplementService.createSupplement(request, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/list")
    public ResponseEntity<List<SupplementResponse>> listSupplement(Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                supplementService.listSupplement(authentication.getName())
        );

    }

    @GetMapping("list/{id}")
    public ResponseEntity<SupplementResponse> getSupplement
            (Authentication authentication, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(supplementService.getSupplement(id, authentication.getName()));

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteSupplement
            (@PathVariable Long id, Authentication authentication) {


        supplementService.deleteSupplement(id, authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(
                "Supplement is Deleted"
        );

    }


    @PatchMapping("/edit/{id}")
    ResponseEntity<SupplementResponse> editSupplement
            (@PathVariable Long id, @Valid @RequestBody SupplementRequest request,
             Authentication authentication
            ) {

        return ResponseEntity.status(HttpStatus.OK).body(
                supplementService.editSupplement(id, request, authentication.getName())
        );

    }


}
