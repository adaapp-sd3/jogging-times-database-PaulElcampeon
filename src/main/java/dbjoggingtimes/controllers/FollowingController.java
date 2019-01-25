package dbjoggingtimes.controllers;

import dbjoggingtimes.models.Following;
import dbjoggingtimes.models.dto.FollowRequestDto;
import dbjoggingtimes.services.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowingController {

    @Autowired
    private FollowingService followingService;

    @RequestMapping(value="/add-follower", method=RequestMethod.POST)
    public void addFollower(@RequestBody FollowRequestDto followRequestDto) {
        followingService.addNewFollowing(new Following(followRequestDto.getFollowersId(), followRequestDto.getFollowedId()));
    }

    @RequestMapping(value="/remove-follower", method=RequestMethod.DELETE)
    public void deleteFollower(@RequestBody FollowRequestDto followRequestDto) {
        followingService.removeFollowing(followRequestDto.getFollowersId(), followRequestDto.getFollowedId());
    }
}
