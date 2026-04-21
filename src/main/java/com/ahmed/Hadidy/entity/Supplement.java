package com.ahmed.Hadidy.entity;


import jakarta.persistence.*;

@Entity
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name ="price")
    private double price;

    @Column(name = "picture")
    private String picture ;

    @Column(name = "is_public")
    private boolean isPublic ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
