package dbjoggingtimes.models.dto;

import lombok.Data;

@Data
public class AddJogRequest {

    private String date;
    private int totalTime;
    private int totalDistance;
    private Integer userId;
}
