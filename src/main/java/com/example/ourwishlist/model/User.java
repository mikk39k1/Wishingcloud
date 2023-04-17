package com.example.ourwishlist.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class User {


    private  Integer accountsId;

    @NotBlank (message = "Username field can not be empty")
    @Size(min = 3, max = 20, message = "username must contain only letters")
    @Pattern(regexp = "^[a-zA-ZøæåØÆÅ0-9]+$", message = "Username must contain only letters")
    private String username;

    @NotBlank (message = "Password field can not be empty")
    @Size(min = 6, max = 20, message = "password must be min. 8 characters")
    @Pattern(regexp = "^[a-zA-ZøæåØÆÅ./!@0-9]+$", message = "Password contains invalid characters")
    private String passwd;


    @NotBlank (message = "First name field can not be empty")
    @Pattern(regexp = "^[a-zA-ZøæåØÆÅ]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank (message = "Last name field can not be empty")
    @Pattern(regexp = "^[a-zA-ZøæåØÆÅ.-]+$", message = "Last name must contain only letters")
    private String lastName;

    @NotBlank (message = "Email field can not be empty")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email must be a valid provider")
    private String email;

    @NotBlank (message = "Address field can not be empty")
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "accountsId=" + accountsId +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
