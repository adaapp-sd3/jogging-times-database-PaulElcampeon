package dbjoggingtimes.models.dto;

import lombok.Data;

@Data
public class SignInDtoResponse {

    public boolean success;
    public String message;
    public String optional;

    public SignInDtoResponse(){}

    public SignInDtoResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }


    public SignInDtoResponse(boolean success, String message, String optional){
        this.success = success;
        this.message = message;
        this.optional = optional;
    }


}
