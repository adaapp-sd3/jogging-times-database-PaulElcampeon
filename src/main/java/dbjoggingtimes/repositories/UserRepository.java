package dbjoggingtimes.repositories;

import dbjoggingtimes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email=?1")
    public User findByEmail(String email);

    @Query("select u from User u where u.id<>?1")
    List<User> findAllOtherUsers(Integer id);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id=?1")
    void deleteByUserId(int id);

}
