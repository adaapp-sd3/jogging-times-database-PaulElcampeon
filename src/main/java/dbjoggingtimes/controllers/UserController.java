package dbjoggingtimes.controllers;

import dbjoggingtimes.models.User;
import dbjoggingtimes.models.dto.CreateUserDtoRequest;
import dbjoggingtimes.models.dto.CreateUserDtoResponse;
import dbjoggingtimes.models.dto.GetFollowersDtoResponse;
import dbjoggingtimes.models.dto.UserIdDto;
import dbjoggingtimes.services.AuthenticationService;
import dbjoggingtimes.services.FollowingService;
import dbjoggingtimes.services.JogService;
import dbjoggingtimes.services.UserService;
import dbjoggingtimes.utility.SecurePasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SecurePasswordUtility securePasswordUtility;

    @Autowired
    private UserService userService;

    @Autowired
    private JogService jogService;

    @Autowired
    private FollowingService followingService;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/create-user", method=RequestMethod.POST)
    public CreateUserDtoResponse addUser(@RequestBody @Valid CreateUserDtoRequest createUserDtoRequest, BindingResult result) {

        if(result.hasErrors()){
            return new CreateUserDtoResponse(false, "emptyfields");
        }

        if(userService.checkIfUserAlreadyExists(createUserDtoRequest.getEmail())){
            return new CreateUserDtoResponse(false, "email already in use");
        }

        if(authenticationService.checkPasswordsMatch(createUserDtoRequest.getPassword(), createUserDtoRequest.getConfirmPassword())){
            String salt = securePasswordUtility.getSalt(20);
            String hashedPassword = securePasswordUtility.generateSecurePassword(createUserDtoRequest.getPassword(), salt);
            User newUser = new User(createUserDtoRequest.getName(), createUserDtoRequest.getEmail(), salt, hashedPassword);
            userService.addUser(newUser);
            return new CreateUserDtoResponse(true, "non", userService.getUserByEmail(createUserDtoRequest.getEmail()).getId());
        } else {
            return new CreateUserDtoResponse(false, "passwordsNonMatch");
        }
    }

    @RequestMapping(value="/delete-user", method=RequestMethod.DELETE)
    public void deleteUser(@RequestBody UserIdDto userIdDto) {
        userService.removeUserById(userIdDto.getUserId());
        jogService.removeJogByUserId(userIdDto.getUserId());
        followingService.removeAllMyFollowersAndPeopleImFollowing(userIdDto.getUserId());
    }

    //Gets all users expect for the user making the request
    @RequestMapping(value = "/user-all/{userId}", method = RequestMethod.GET)
    public GetFollowersDtoResponse getAllUsers(@PathVariable Integer userId){
        GetFollowersDtoResponse getFollowersDtoResponse = new GetFollowersDtoResponse();
        getFollowersDtoResponse.setUsersImNotFollowing(userService.getThePeopleImNotFollowing(userId));
        getFollowersDtoResponse.setPeopleFollowingMe(userService.getThePeopleFollowingMe(userId));
        getFollowersDtoResponse.setPeopleImFollowing(userService.getThePeopleImFollowing(userId));
        return getFollowersDtoResponse;
    }


}
