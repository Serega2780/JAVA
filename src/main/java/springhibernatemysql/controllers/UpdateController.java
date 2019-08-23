package springhibernatemysql.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.User;
import springhibernatemysql.repositories.UserRepository;
import springhibernatemysql.service.UserServiceImpl;

@Controller
@RequestMapping(value = {
        "/admin/edit",
        "/admin/update"
})
public class UpdateController {
    private UserServiceImpl userService;

    @Autowired
    public UpdateController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-form";

    }

    @PostMapping
    public ModelAndView editUser(@RequestParam("id") int id,
                                 @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 @RequestParam("email") String email,
                                 @RequestParam("country") String country) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        user.setCountry(country);
        userService.createOrUpdateUser(user);
        return new ModelAndView("redirect:/admin/list");

    }
}
