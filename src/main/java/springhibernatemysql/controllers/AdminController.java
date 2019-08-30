package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
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

        List<String> countries = new ArrayList<String>();
        countries.add("India");
        countries.add("United States");
        countries.add("Japan");
        countries.add("Australia");
        countries.add("Canada");

        return countries;
    }


    @GetMapping("/admin/list")
    public String home(Model model) {
        model.addAttribute("listUser", userService.getAllUsers());

        System.out.println(userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/admin/new")
    public ModelAndView addForm(@ModelAttribute(name = "countriesList") List<String> countries) {

        ModelAndView modelView = new ModelAndView("user-form");

        List<Role> roles = roleService.getAllRoles();
        modelView.addObject("grantedAuthorities", roles);

        return modelView;
    }

    @PostMapping("/admin/insert")
    public String addUser(@ModelAttribute User user, @RequestParam("role") String role) {

        if (role.contains("ROLE_ADMIN")) {
            user.getGrantedAuthorities().add(roleService.getSingleRole(1));
        }

        user.getGrantedAuthorities().add(roleService.getSingleRole(2));
        userService.createUser(user);

        return "redirect:/admin/list";
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/list");

    }

    @GetMapping("/admin/edit")
    public ModelAndView showEditForm(@RequestParam("id") int id) {
        User user = userService.getUserById(id);
        ModelAndView modelView = new ModelAndView("user-form");
        modelView.addObject("user", user);

        modelView.addObject("grantedAuthorities", roleService.getAllRoles());

        return modelView;
    }

    @PostMapping("/admin/update")
    public String editUser(@ModelAttribute User user, @RequestParam("role") String role) {
        //User u = userService.getUserById(user.getId());
        if (role.contains("ROLE_ADMIN")) {
            user.getGrantedAuthorities().add(roleService.getSingleRole(1));
        }

        user.getGrantedAuthorities().add(roleService.getSingleRole(2));
        userService.createUser(user);

        return "redirect:/admin/list";

    }
}
