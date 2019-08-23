package springhibernatemysql.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.User;
import springhibernatemysql.repositories.UserRepository;
import springhibernatemysql.service.UserServiceImpl;

import java.security.Principal;

@Controller
/*@RequestMapping(value = {
        "/new",
        "/insert"
})*/
public class AddController {
    private UserServiceImpl userService;

    @Autowired
    public AddController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping("/admin/new")
    public String addForm() {

        return "user-form";
    }

    @PostMapping("/admin/insert")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("password") String password,
                                @RequestParam("role") String role, @RequestParam("email") String email,
                                @RequestParam("country") String country,
                                Principal principal) {


        User user = new User(name, password, role, email, country);
        //  User loginedUser = (User) ((Authentication) principal).getPrincipal();
        userService.createOrUpdateUser(user);
        return new ModelAndView("redirect:/admin/list");

    }
}
