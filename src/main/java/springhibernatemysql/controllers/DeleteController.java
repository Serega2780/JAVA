package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.repositories.UserRepository;
import springhibernatemysql.service.UserServiceImpl;

@Controller
@RequestMapping(value = {
        "/admin/delete"
})
public class DeleteController {
    private UserServiceImpl userService;

    @Autowired
    public DeleteController(UserServiceImpl userService) {

        this.userService = userService;
    }

    @GetMapping
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/list");

    }
}
