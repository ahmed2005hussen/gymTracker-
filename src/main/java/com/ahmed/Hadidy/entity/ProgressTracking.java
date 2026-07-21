//package com.ahmed.Hadidy.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.Locale;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ProgressTracking {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id ;
//
//    @Column(name = "weight")
//    private double weight ;
//
//    @Column(name = "height")
//    private double height ;
//
//    @Column(name = "note")
//    private String note;
//
//    @Column(name = "taken_data")
//    private LocalDate takenDate ;
//
//    @Column(name = "photo")
//    private String photo;
//
//    @ManyToOne
//    @JoinColumn(name = "profile_id")
//    private Profile profile ;
//
//}
