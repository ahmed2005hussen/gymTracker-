package com.ahmed.Hadidy.dto.request;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplementRequest {

    private String name;

    private String description;

    @PositiveOrZero
    private Double price;

    private String picture;

}
