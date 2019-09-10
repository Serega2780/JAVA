package springhibernatemysql.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.PopulateCountries;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {
        "/new-user/**"
})
public class SignupController {

    private PopulateCountries populateCountries;
    private RoleService roleService;
    private UserService userService;

    public SignupController(PopulateCountries populateCountries, RoleService roleService,
                            UserService userService) {
        this.populateCountries = populateCountries;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/new-user")
    public ModelAndView showNewUserForm() {
        ModelAndView modelView = new ModelAndView("/new-user.html");
        modelView.addObject("user", new User());
        modelView.addObject("countriesList", populateCountries.populateCountries());
        return modelView;
    }

    @PostMapping("/new-user/insert")
    public String addNewUser(@ModelAttribute User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getSingleRole(2));
        user.setGrantedAuthorities(roles);
        userService.createUser(user);

        return "redirect:/";
    }
}
