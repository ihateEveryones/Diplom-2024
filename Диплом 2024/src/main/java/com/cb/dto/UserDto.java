package com.cb.dto;

import com.cb.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    @NotEmpty(message = "Please enter valid name.")
    private String name;

    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    @NotEmpty(message = "Please enter valid password.")
    private String password;

    @NotEmpty(message = "Please enter valid password.")
    private String surname;
    @NotEmpty(message = "Please enter valid password.")
    private String patronymic;

    @NotEmpty(message = "Please enter valid password.")
    private String phone_number;
    private String image;
    private String verificationCode;

}
