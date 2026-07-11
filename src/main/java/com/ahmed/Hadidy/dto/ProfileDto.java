package com.ahmed.Hadidy.dto;

import com.ahmed.Hadidy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

        private String fullName;

        private Double weight;

        private Double height;

        private Double gymPrice;

        private String goal;

        private LocalDate startSubscribe;

        private LocalDate endSubscribe;

        private String profilePicture;

        private String gmail;

}
