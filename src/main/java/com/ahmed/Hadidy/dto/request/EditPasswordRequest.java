package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPasswordRequest {

    @NotBlank
    private String oldPass ;

    @Size(min = 8)
    @NotBlank
    private String newPass;

}
