package com.ahmed.Hadidy.entity;

import com.ahmed.Hadidy.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id ;

    @Column(name = "username" , unique = true , nullable = false)
    private String username ;

    @Column(name = "password")
    @Size(min = 8)
    private String password ;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER ;

    @Column(name = "enabled")
    private boolean enabled = true ;

    @OneToOne(mappedBy = "user"  , cascade = CascadeType.ALL)
    private Profile profile ;


    public User(String username , String password , Role role , boolean enabled){
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled ;
    }

    public User(String username , String password){
        this.username = username;
        this.password = password;

    }
}
