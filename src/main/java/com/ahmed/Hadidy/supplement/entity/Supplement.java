package com.ahmed.Hadidy.supplement.entity;
import com.ahmed.Hadidy.audit.entity.BaseEntity;
import com.ahmed.Hadidy.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supplement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile ;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name ="price")
    private double price;

    @Column(name = "picture")
    private String picture ;


}
