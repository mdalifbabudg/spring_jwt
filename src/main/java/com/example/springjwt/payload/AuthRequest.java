package com.example.springjwt.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
    @NotNull @Email @Length(min = 5, max = 50)
    private String email;
    @NotNull @Length(min = 5, max = 10)
    private String password;
}
