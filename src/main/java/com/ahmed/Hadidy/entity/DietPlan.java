package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "title")
    private String title;

    @Column(name= "description")
    private String description ;

    @Column(name = "is_public")
    private boolean isPublic ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @OneToMany(mappedBy= "dietPlan")
    private Set<Meal> meal = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Meal> getMeal() {
        return meal;
    }

    public void setMeal(Set<Meal> meal) {
        this.meal = meal;
    }
}
