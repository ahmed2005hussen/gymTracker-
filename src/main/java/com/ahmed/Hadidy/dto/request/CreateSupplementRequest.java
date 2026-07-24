package com.ahmed.Hadidy.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSupplementRequest {

    @NotBlank
    private String name;

    private String description;

    @PositiveOrZero
    @NotNull
    private Double price ;

    private String picture;

}
