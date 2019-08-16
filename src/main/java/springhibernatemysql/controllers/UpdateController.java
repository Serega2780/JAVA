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

@Controller
@RequestMapping(value = {
        "/edit",
        "/update"
})
public class UpdateController {
    private UserRepository userRepo;

    @Autowired
    public UpdateController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userRepo.findById(id).get();
        model.addAttribute("user", user);
        return "user-form";

    }

    @PostMapping
    public ModelAndView editUser(@RequestParam("name") String name, @RequestParam("password") String password,
                                 @RequestParam("role") String role, @RequestParam("email") String email,
                                 @RequestParam("country") String country) {
        User user = new User(name, password, role, email, country);
        userRepo.save(user);
        return new ModelAndView("redirect:/users");

    }
}
