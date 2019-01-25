package dbjoggingtimes.models.dto;

import dbjoggingtimes.models.Jog;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class GetJogStatsDtoResponse {

    private List<Jog> myJogs;
    private Optional<Integer> totalTime;
    private Optional<Integer> totalDistance;
//    private double averageSpeed;

    public GetJogStatsDtoResponse(){}

    public GetJogStatsDtoResponse(List<Jog> myJogs, Optional<Integer> totalTime, Optional<Integer> totalDistance) {
        this.myJogs = myJogs;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
    }

//    public GetJogStatsDtoResponse(List<Jog> myJogs, long totalTime, long totalDuration, double averageSpeed) {
//        this.myJogs = myJogs;
//        this.totalTime = totalTime;
//        this.totalDuration = totalDuration;
//        this.averageSpeed = averageSpeed;
//    }
}
