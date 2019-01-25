package dbjoggingtimes.models.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SignInDto {

    @Email(message="invalid email")
    private String email;

    @NotNull
    private String password;
}
