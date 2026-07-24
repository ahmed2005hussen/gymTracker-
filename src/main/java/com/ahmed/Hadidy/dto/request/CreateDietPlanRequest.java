package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDietPlanRequest {

    @NotBlank
    private String title;

    private String description ;


}
