package com.myEcom.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
public class UserDto {

    private int userId;
    @NotEmpty
    @Size(min = 4, max = 20, message = "name must be 4 of characters and max of 20 characters")
    private String name;
    @Email(message = "valid email Id is required")
    private String email;
    @NotEmpty
    @Size(min=4, message = "password must be a 4 digit")
    private String password;
    @NotEmpty
    private String about;
    private String gender;
    @NotEmpty
    private String address;
    private Date createAt;
    @NotBlank
    private String phoneNumber;
    private boolean active;
}
