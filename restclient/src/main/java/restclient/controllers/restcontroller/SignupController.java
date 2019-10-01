package restclient.controllers.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restclient.domain.User;
import restclient.service.RoleService;
import restclient.service.UserService;
import restclient.service.implementation.PopulateCountries;

@RestController
@RequestMapping(value = {
        "/new-user/**"
})
public class SignupController {

    private PopulateCountries populateCountries;
    private RoleService roleService;
    private UserService userService;

    public SignupController(PopulateCountries populateCountries, RoleService roleService,
                            UserService userService) {
        this.populateCountries = populateCountries;
        this.roleService = roleService;
        this.userService = userService;
    }

    //REST

    @RequestMapping(value = "/new-user/countries")
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @PostMapping("/new-user/insert")
    @ResponseBody
    public String addUpdateUser(@RequestBody User user) {
        userService.createUser(user);
        return "redirect:/";
    }

}
