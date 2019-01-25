package dbjoggingtimes.services;

import dbjoggingtimes.models.Following;
import dbjoggingtimes.repositories.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    public void addNewFollowing(Following following) {
        followingRepository.save(following);
    }

    public List<Following> getAllPeopleImFollowing(int id){
        return followingRepository.findByFollowersId(id);
    }

    public List<Following> getAllPeopleFollowingMe(int id){
        return followingRepository.findByFollowedId(id);
    }

    public void removeFollowing(int followersId, int followedId){
        followingRepository.removeFollowing(followersId,followedId);
    }

    public void removeAllMyFollowersAndPeopleImFollowing(int id){
        followingRepository.deleteFollowingById(id);
    }

}
