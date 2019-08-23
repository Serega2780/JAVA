package springhibernatemysql.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping(value = {
        "/redirect"
})
public class RedirectorController extends HttpServlet {
    @GetMapping
    public ModelAndView getMapping() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = ((UserDetails) principal).getAuthorities().iterator().next().toString();
        if (role.equals("ADMIN")) {
            return new ModelAndView("redirect:/admin/list");

        }
        return new ModelAndView("redirect:/user");

    }


}

