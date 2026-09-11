package com.ahmed.Hadidy.workoutPlan.entity;

import com.ahmed.Hadidy.audit.entity.BaseEntity;
import com.ahmed.Hadidy.profile.entity.Profile;
import com.ahmed.Hadidy.workoutDay.entity.WorkoutDay;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name ;

    @Column(name ="description")
    private String description ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "workoutPlan")
    private List<WorkoutDay> workoutDays = new ArrayList<>();

    @Column(name = "picture")
    private String picture ;

}
