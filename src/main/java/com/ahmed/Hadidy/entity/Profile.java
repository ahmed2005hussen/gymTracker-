package com.ahmed.Hadidy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id ;

    @Column(name="full_name")
    private String fullName;

    @Column(name="weight")
    private double weight ;

    @Column(name="height")
    private double height  ;

    @Column(name ="bmi")
    private double bmi = 0.0;

    @Column(name="gym_price")
    private double gymPrice ;

    @Column(name="goal")
    private String goal ;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @Column(name="start_subscribe")
    private LocalDate startSubscribe ;

    @Column(name="end_subscribe")
    private LocalDate endSubscribe ;

    @Column(name="profile_picture")
    private String profilePicture;

    @Column(name="gmail")
    private String gmail ;

}
