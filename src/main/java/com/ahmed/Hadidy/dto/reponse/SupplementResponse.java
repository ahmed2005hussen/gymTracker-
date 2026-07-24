package com.ahmed.Hadidy.dto.reponse;


import com.ahmed.Hadidy.entity.Supplement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplementResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String picture;

    public SupplementResponse(Supplement s) {
        this.id = s.getId();
        this.name = s.getName();
        this.description = s.getDescription();
        this.picture = s.getPicture();
        this.price = s.getPrice();
    }

}
