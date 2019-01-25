package dbjoggingtimes.repositories;

import dbjoggingtimes.models.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Integer> {


    @Query("select u from Following u where u.followersId=?1")
    public List<Following> findByFollowersId(Integer followersId);

    @Query("select u from Following u where u.followedId=?1")
    public List<Following> findByFollowedId(Integer followedId);

    @Modifying
    @Transactional
    @Query("delete from Following u where u.followersId=?1 and u.followedId=?2")
    void removeFollowing(int followersId, int followedId);

    @Modifying
    @Transactional
    @Query("delete from Following u where u.followersId=?1 or u.followedId=?1")
    void deleteFollowingById(int followersId);

}
