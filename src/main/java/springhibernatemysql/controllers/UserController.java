package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springhibernatemysql.service.UserServiceImpl;

@Controller
@RequestMapping(value = {
        "/user"
})
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping
    public String userHome(Model model) {
    //    model.addAttribute("listUser", userService.selectUsersByRole("USER"));
        return "user-access";
    }
}
