package dbjoggingtimes.controllers;

import dbjoggingtimes.models.User;
import dbjoggingtimes.models.dto.SignInDto;
import dbjoggingtimes.models.dto.SignInDtoResponse;
import dbjoggingtimes.services.AuthenticationService;
import dbjoggingtimes.services.JogService;
import dbjoggingtimes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignIController {

    @Autowired
    private UserService userService;

    @Autowired
    private JogService jogService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value="/sign-in", method=RequestMethod.POST)
    public SignInDtoResponse signIn(@RequestBody @Valid SignInDto signInDto, BindingResult result) {
        System.out.printf("User with email %s tried to login ", signInDto.getEmail());
        if(result.hasErrors()) {
            return new SignInDtoResponse(false, result.getFieldError("email").getDefaultMessage());
        }

        User user1;

        if(userService.getUserByEmail(signInDto.getEmail())!= null){
            user1 = userService.getUserByEmail(signInDto.getEmail());
        }else {
            return new SignInDtoResponse(false, "non-exist");
        }

        if(authenticationService.authenticatePassword(
                user1.getSalt(),
                signInDto.getPassword(),
                user1.getSecurePassword()));
        System.out.printf("We just sent out user id: %s", user1.getId());
            return new SignInDtoResponse(true, user1.getName(), Integer.toString(user1.getId()));

    }

}
