package com.ahmed.Hadidy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPasswordRequest {

    private String oldPass ;
    private String newPass;

}
