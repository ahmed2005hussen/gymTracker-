package com.ahmed.Hadidy.entity;
import com.ahmed.Hadidy.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id ;

    @Column(name = "username" , unique = true , nullable = false)
    private String username ;

    @Column(name = "password")
    private String password ;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER ;

    @Column(name = "enabled")
    private boolean enabled = true ;

    @OneToOne(mappedBy = "user"  , cascade = CascadeType.ALL)
    private Profile profile ;
}
