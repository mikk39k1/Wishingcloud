package com.example.ourwishlist.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Username field cant be empty")
    @Size(min = 3, max = 20, message = "Username must contain only letters")
    private String username;

    @NotBlank(message = "Password field cant be empty")
    @Size(min = 6, max = 20, message = "Password must be min. 8 characters")
    @Pattern(regexp = "^[a-zA-ZøæåØÆÅ./!@]+$", message = "Password contains invalid characters")
    private String passwd;


    public enum LoginResult {
        SUCCESS,
        INCORRECT_USERNAME,
        INCORRECT_PASSWORD
    }




}
