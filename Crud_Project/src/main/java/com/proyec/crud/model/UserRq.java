package com.proyec.crud.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRq {
    @NotBlank(message = "Este campo es obligatorio")
    private String name;
    private String email;
    private  String password;

}
