package com.ravionics.controller;


import com.ravionics.model.Users;
import com.ravionics.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("users/login");
        return modelAndView;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        Users users = new Users();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/signup");
        return modelAndView;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Validated Users users, BindingResult bindingResult) {
        ModelAndView modelAndView= new ModelAndView();
        Users usersExists=usersService.findUsersByEmail(users.getEmail());

        if(usersExists != null) {
            bindingResult.rejectValue("email", "error.users", "This username already exists!");
        }
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("users/signup");
        } else {
            usersService.saveUsers(users);
            modelAndView.addObject("msg", "Congratulations!! New users has been registered successfully!");
            modelAndView.addObject("users", new Users());
            modelAndView.setViewName("users/signup");
        }
        return modelAndView;
    }


    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersService.findUsersByEmail(auth.getName());
        modelAndView.addObject("userName", users.getFirstname() + " " + users.getLastname());
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/access_denied");
        return modelAndView;
    }
}