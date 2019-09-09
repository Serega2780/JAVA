package springhibernatemysql.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {
        "/admin/**"
})
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {

        this.roleService = roleService;
        this.userService = userService;
    }

    @ModelAttribute(name = "countriesList")
    public List<String> populateCountries() {

        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("United States");
        countries.add("Japan");
        countries.add("Australia");
        countries.add("Canada");

        return countries;
    }


    @GetMapping("/admin/list")
    public String home(@ModelAttribute(name = "countriesList") List<String> countries,Model model) {
        model.addAttribute("listUser", userService.getAllUsers());

        System.out.println(userService.getAllUsers());
        return "/user-list.html";
    }

    @GetMapping("/admin/new")
    public ModelAndView addForm(@ModelAttribute(name = "countriesList") List<String> countries) {

        ModelAndView modelView = new ModelAndView("/user-form-new.html");

        List<Role> roles = roleService.getAllRoles();
        modelView.addObject("roles", roles);
        modelView.addObject("user", new User());

        return modelView;
    }

    @PostMapping("/admin/insert")
    public String addUser(@ModelAttribute User user, @RequestParam("roles") String roles) {
        List<Role> authorities = new ArrayList<>();
        if (roles.contains("ROLE_ADMIN")) {
            authorities.add(roleService.getSingleRole(1));
        }

        authorities.add(roleService.getSingleRole(2));
        user.setGrantedAuthorities(authorities);
        userService.createUser(user);

        return "redirect:/admin/list";
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/list");

    }
/*
    @GetMapping("/admin/edit")
    public ModelAndView showEditForm(@RequestParam("id") int id) {
        User user = userService.getUserById(id);
        ModelAndView modelView = new ModelAndView("/user-form-new.html");
        modelView.addObject("user", user);

        modelView.addObject("roles", roleService.getAllRoles());

        return modelView;
    }
*/
}
