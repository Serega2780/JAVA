package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import springhibernatemysql.repositories.UserRepository;
import springhibernatemysql.service.UserServiceImpl;

@Controller
@RequestMapping(value = {
        "/admin/list"
})
public class AdminController {

    private UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("listUser", userService.getAllUsers());
        return "user-list";
    }
}
