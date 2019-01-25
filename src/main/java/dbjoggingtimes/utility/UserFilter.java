package dbjoggingtimes.utility;

import dbjoggingtimes.models.Following;
import dbjoggingtimes.models.User;

import java.util.List;
import java.util.function.Predicate;

public class UserFilter {

    public static Predicate<User> matchesFollowedId(List<Following> followers) {
        return user -> {
            for (Following following : followers) {
                if (user.getId() == following.getFollowedId()) {
                    return true;
                }
            }
            return false;
        };
    }

    public static Predicate<User> matchesFollowerId(List<Following> followers) {
        return user -> {
            for (Following following : followers) {
                if (user.getId() == following.getFollowersId()) {
                    return true;
                }
            }
            return false;
        };
    }

}
