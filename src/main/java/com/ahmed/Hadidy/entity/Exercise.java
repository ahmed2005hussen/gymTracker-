package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name;

    @Column(name ="description")
    private String description ;

    @Column(name ="reps")
    private int repeat ;

    @Column(name ="sets")
    private int sets ;

    @Column(name ="picture")
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_day_id", nullable = false)
    private WorkoutDay workoutDay;
}
