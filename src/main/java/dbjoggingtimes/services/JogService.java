package dbjoggingtimes.services;

import dbjoggingtimes.models.Jog;
import dbjoggingtimes.repositories.JogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogService {

    @Autowired
    private JogRepository jogRepository;

    public void add(Jog jog) {
        jogRepository.save(jog);
    }

    public void removeJogId(Integer jogId){
        jogRepository.deleteById(jogId);
    }

    public void removeJogByUserId(Integer userId){
        jogRepository.deleteByUserId(userId);
    }

    public List<Jog> findAllMyJogs(Integer id){
        List<Jog> myJogs = jogRepository.findJogByUserId(id);
        return myJogs;
    }

    public void updateJog(Jog jog){
        jogRepository.updateJogStatus(jog.getDate(), jog.getTotalTime(), jog.getTotalDistance(), jog.getId());
    }

    public Optional<Integer> getJogTotalTimes(Integer id){
        return jogRepository.findTotalTimeByUserId(id);
    }

    public Optional<Integer> getJogTotalDistance(Integer id){
        return jogRepository.findTotalDistanceByUserId(id);
    }
}
