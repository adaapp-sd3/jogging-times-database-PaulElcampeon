package dbjoggingtimes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(){
        return "sign-in.html";
    }

    @RequestMapping(value = "/create-account", method = RequestMethod.GET)
    public String createAccount(){
        System.out.println("someone requested the create accounts page");
        return "create-account.html";
    }

    @RequestMapping(value = "/create-time", method = RequestMethod.GET)
    public String createTime(){
        return "create-time.html";
    }

    @RequestMapping(value = "/edit-time", method = RequestMethod.GET)
    public String editTime(){
        return "edit-time.html";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String signInPage(){
        return "sign-in.html";
    }

    @RequestMapping(value = "/sign-out", method = RequestMethod.GET)
    public String signOutPage(){
        return "sign-out.html";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(){
        return "profile.html";
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public String followersPage(){
        return "followers.html";
    }

    @RequestMapping(value ="/delete-successful", method = RequestMethod.GET)
    public String backToSignIn(){
        return "sign-in.html";
    }

}
