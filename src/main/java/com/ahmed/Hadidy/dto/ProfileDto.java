package com.ahmed.Hadidy.dto;

import com.ahmed.Hadidy.entity.Profile;
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

        private double bmi ;

        public ProfileDto(Profile p) {
                this.fullName = p.getFullName();
                this.weight = p.getWeight();
                this.height = p.getHeight();
                this.gymPrice = p.getGymPrice();
                this.goal = p.getGoal();
                this.startSubscribe = p.getStartSubscribe();
                this.endSubscribe = p.getEndSubscribe();
                this.profilePicture = p.getProfilePicture();
                this.gmail = p.getGmail();
                this.bmi = p.getBmi();
        }
}
