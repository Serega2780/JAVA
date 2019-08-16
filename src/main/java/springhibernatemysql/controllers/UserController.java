package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import springhibernatemysql.repositories.UserRepository;

@Controller
@RequestMapping(value = {
        "/users",
        "/list"
})
public class UserController {

    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("listUser", userRepo.findAll());
        return "user-list";
    }
}
