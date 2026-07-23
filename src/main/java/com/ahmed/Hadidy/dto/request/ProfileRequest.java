package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {

        private String fullName;

        @Positive
        private Double weight;

        @Positive
        private Double height;

        @PositiveOrZero
        private Double gymPrice;

        private String goal;

        private LocalDate startSubscribe;

        private LocalDate endSubscribe;

        private String profilePicture;

        @Email
        private String gmail;


}
