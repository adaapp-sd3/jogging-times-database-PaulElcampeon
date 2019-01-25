package dbjoggingtimes.controllers;

import dbjoggingtimes.models.Jog;
import dbjoggingtimes.models.dto.AddJogRequest;
import dbjoggingtimes.models.dto.DeleteJogDtoRequest;
import dbjoggingtimes.models.dto.GetJogStatsDtoResponse;
import dbjoggingtimes.services.JogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JogController {

    @Autowired
    private JogService jogService;

    @RequestMapping(value="/add-jog", method = RequestMethod.POST)
    public void addJog(@RequestBody AddJogRequest addJogRequest) {
        jogService.add(new Jog(addJogRequest.getDate(), addJogRequest.getTotalTime(), addJogRequest.getTotalDistance(), addJogRequest.getUserId()));
    }

    @RequestMapping(value="/delete-jog", method = RequestMethod.DELETE)
    public void deleteJog(@RequestBody DeleteJogDtoRequest deleteJogDtoRequest) {
        jogService.removeJogId(deleteJogDtoRequest.getJogId());
    }

    @RequestMapping(value="/update-jog", method = RequestMethod.PUT)
    public void updateJog(@RequestBody Jog jog) {
        jogService.updateJog(jog);
    }

    @RequestMapping(value = "/jogs/{userId}", method = RequestMethod.GET)
    public GetJogStatsDtoResponse getMyJogs(@PathVariable Integer userId){
        System.out.println();
        System.out.printf("User with id: %d tried to retrieve their jogs", userId);
        return new GetJogStatsDtoResponse(jogService.findAllMyJogs(userId), jogService.getJogTotalTimes(userId), jogService.getJogTotalDistance(userId));
    }
}
