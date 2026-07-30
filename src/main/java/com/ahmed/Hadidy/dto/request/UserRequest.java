package com.ahmed.Hadidy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "username should not be null")
    @NotBlank(message = "enter valid username")
    private String username ;

    @Size(min = 8)
    @NotBlank(message = "enter valid Pass")
    private String password ;

}
