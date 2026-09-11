package com.ahmed.Hadidy.diet.entity;

import com.ahmed.Hadidy.audit.entity.BaseEntity;
import com.ahmed.Hadidy.meal.entity.Meal;
import com.ahmed.Hadidy.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DietPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "title")
    private String title;

    @Column(name= "description")
    private String description ;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile ;

    @OneToMany(mappedBy= "dietPlan")
    private Set<Meal> meal = new HashSet<>();


}
