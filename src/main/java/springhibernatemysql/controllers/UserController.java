package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.UserServiceImpl;

import java.util.List;

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
    public ModelAndView userHome(Model model) {
        ModelAndView modelView = new ModelAndView("user-access");

        List<User> listUser = userService.selectUsersByRole();
        modelView.addObject("listUser", listUser);

        return modelView;

    }
}
