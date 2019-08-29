package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {
        "/admin/**"
})
public class AdminController {

    private UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {

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
    public ModelAndView addForm(@ModelAttribute(name = "countriesList") List<String> countries,
                                ) {

        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("user-form");

        return modelView;
    }

    @PostMapping("/admin/insert")
    public String addUser(@ModelAttribute User user) {
        String name;
        name = user.getName();
        //   User user = new User(name, password, email, country);
        //  User loginedUser = (User) ((Authentication) principal).getPrincipal();
        //    userService.createOrUpdateUser(user);
        // return new ModelAndView("redirect:/admin/list");
        // name = httpServletRequest.getParameter("role") != null;


        return user.getName();
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        //    userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/list");

    }

    @GetMapping("/admin/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        //  User user = userService.getUserById(id);
        //   model.addAttribute("user", user);
        return "user-form";

    }

    @PostMapping("/admin/update")
    public ModelAndView editUser(@RequestParam("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 @RequestParam("email") String email,
                                 @RequestParam("country") String country) {
     /*   User user = userService.getUserById(id);
        user.setName(name);
        user.setPassword(password);
    //    user.setRole(role);
        user.setEmail(email);
        user.setCountry(country);
        userService.createOrUpdateUser(user);

      */
        return new ModelAndView("redirect:/admin/list");

    }
}
