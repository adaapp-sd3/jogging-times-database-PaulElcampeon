package dbjoggingtimes.services;

import dbjoggingtimes.utility.UserFilter;
import dbjoggingtimes.models.Following;
import dbjoggingtimes.repositories.UserRepository;
import dbjoggingtimes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FollowingService followingService;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void removeUserById(int id){
        userRepository.deleteByUserId(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsersExceptMe(int id){
        return userRepository.findAllOtherUsers(id);
    }

    @Transactional
    public void insertWithQuery(User user) {
        entityManager.createNativeQuery("INSERT INTO Users (id,name,email,salt,secure_Password) VALUES (?,?,?,?,?)")
                .setParameter(1, user.getId())
                .setParameter(2, user.getName())
                .setParameter(3, user.getEmail())
                .setParameter(4, user.getSalt())
                .setParameter(5, user.getSecurePassword())
                .executeUpdate();
    }

    public List<User> getThePeopleImNotFollowing(int id){
        List<User> allUsers = getAllUsersExceptMe(id);
        List<Following> allTheIdOfPeopleImFollowing = followingService.getAllPeopleImFollowing(id);
        return allUsers.stream().filter(UserFilter.matchesFollowedId(allTheIdOfPeopleImFollowing).negate()).collect(Collectors.toList());
    }

    public List<User> getThePeopleImFollowing(int id){
        List<User> allUsers = getAllUsersExceptMe(id);
        List<Following> allTheIdOfPeopleImFollowing = followingService.getAllPeopleImFollowing(id);
        return allUsers.stream().filter(UserFilter.matchesFollowedId(allTheIdOfPeopleImFollowing)).collect(Collectors.toList());
    }

    public List<User> getThePeopleFollowingMe(int id){
        List<User> allUsers = getAllUsersExceptMe(id);
        List<Following> allTheIdOfPeopleFollowingMe = followingService.getAllPeopleFollowingMe(id);
        return allUsers.stream().filter(UserFilter.matchesFollowerId(allTheIdOfPeopleFollowingMe)).collect(Collectors.toList());
    }

    public boolean checkIfUserAlreadyExists(String email){
        return userRepository.findByEmail(email)!=null;
    }
}
