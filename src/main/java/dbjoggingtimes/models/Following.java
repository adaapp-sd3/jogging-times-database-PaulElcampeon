package dbjoggingtimes.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FOLLOWINGS")
public class Following {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false)
    private Integer followersId;

    @Column(nullable=false)
    private Integer followedId;

    public Following(){}

    public Following(Integer followersId, Integer followedId){
        this.followedId = followedId;
        this.followersId = followersId;
    }

}
