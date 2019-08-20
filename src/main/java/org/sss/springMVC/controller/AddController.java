package org.sss.springMVC.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.sss.springMVC.domain.User;
import org.sss.springMVC.repository.UserRepository;
import org.sss.springMVC.service.UserService;
import org.sss.springMVC.service.UserServiceImpl;


@Controller
/*@RequestMapping(value = {
        "/new",
        "/insert"
})*/
public class AddController {

    private final UserService userService;

    @Autowired
    public AddController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String addForm() {

        return "user-form";
    }

    @PostMapping("/insert")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("password") String password,
                                @RequestParam("role") String role, @RequestParam("email") String email,
                                @RequestParam("country") String country) {


        User user = new User(name, password, role, email, country);
        userService.createOrUpdateUser(user);
        return new ModelAndView("redirect:/users");

    }
}
