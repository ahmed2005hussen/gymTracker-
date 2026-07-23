package com.ahmed.Hadidy.dto.reponse;

import com.ahmed.Hadidy.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProfileResponse {

        private Long id;

        private String fullName;

        private Double weight;

        private Double height;

        private Double gymPrice;

        private String goal;

        private LocalDate startSubscribe;

        private LocalDate endSubscribe;

        private String profilePicture;

        private String gmail;

        private Double bmi ;

        public ProfileResponse(Profile p) {
                this.id = p.getId();
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
