package dbjoggingtimes.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="JOGS")
public class Jog {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String date;

    @Column(nullable=false)
    private int totalTime;

    @Column(nullable=false)
    private int totalDistance;

    @Column(nullable=false)
    private Integer userId;

    public Jog(){}

    public Jog(String date, int totalTime, int totalDistance, Integer userId){
        this.date = date;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
        this.userId = userId;
    }


}
