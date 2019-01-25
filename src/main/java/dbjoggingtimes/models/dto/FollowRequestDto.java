package dbjoggingtimes.models.dto;

import lombok.Data;

@Data
public class FollowRequestDto {

    private Integer followersId;
    private Integer followedId;

}
