package dbjoggingtimes.models.dto;

import dbjoggingtimes.models.User;
import lombok.Data;

import java.util.List;

@Data
public class GetFollowersDtoResponse {

    List<User> usersImNotFollowing;
    List<User> peopleImFollowing;
    List<User> peopleFollowingMe;
}
