package dbjoggingtimes.models.dto;

import lombok.Data;

@Data
public class CreateUserDtoResponse {

    public CreateUserDtoResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    private boolean success;
    private String error;
    private Integer userId;

    public CreateUserDtoResponse(){}

    public CreateUserDtoResponse(boolean success, String error, Integer userId) {
        this.success = success;
        this.error = error;
        this.userId = userId;
    }
}
