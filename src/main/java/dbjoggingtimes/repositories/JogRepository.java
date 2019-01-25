package dbjoggingtimes.repositories;

import dbjoggingtimes.models.Jog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogRepository extends JpaRepository<Jog, Integer> {

    @Query("select u from Jog u where u.userId=?1")
    List<Jog> findJogByUserId(Integer id);

    @Query("select SUM(u.totalTime) from Jog u where u.userId=?1")
    Optional<Integer> findTotalTimeByUserId(Integer id);

    @Query("select SUM(u.totalDistance) from Jog u where u.userId=?1")
    Optional<Integer> findTotalDistanceByUserId(Integer id);

    @Modifying
    @Transactional
    @Query("delete from Jog u where userId = ?1")
    void deleteByUserId(int id);

    @Transactional
    @Modifying
    @Query("update Jog u set u.date = ?1, u.totalTime = ?2, u.totalDistance = ?3 where u.id = ?4")
    public void updateJogStatus(String date, int totalTime, int totalDistance, Integer id);

}