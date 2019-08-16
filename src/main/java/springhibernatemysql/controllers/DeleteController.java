package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.repositories.UserRepository;

@Controller
@RequestMapping(value = {
        "/delete"
})
public class DeleteController {
    private UserRepository userRepo;

    @Autowired
    public DeleteController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        userRepo.deleteById(id);
        return new ModelAndView("redirect:/users");

    }
}
