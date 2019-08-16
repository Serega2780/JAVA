package springhibernatemysql.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.User;
import springhibernatemysql.repositories.UserRepository;

@Controller
/*@RequestMapping(value = {
        "/new",
        "/insert"
})*/
public class AddController {
    private UserRepository userRepo;

    @Autowired
    public AddController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/new")
    public String addForm() {

        return "user-form";
    }

    @PostMapping("/insert")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("password") String password,
                                @RequestParam("role") String role, @RequestParam("email") String email,
                                @RequestParam("country") String country) {


        User user = new User(name, password, role, email, country);
        userRepo.save(user);
        return new ModelAndView("redirect:/users");

    }
}
