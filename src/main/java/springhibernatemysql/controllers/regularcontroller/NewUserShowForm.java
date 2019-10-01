package springhibernatemysql.controllers.regularcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewUserShowForm {
    @GetMapping("new-user")
    public ModelAndView showNewUserForm() {
        ModelAndView modelView = new ModelAndView("/new-user.html");
        return modelView;
    }

}
