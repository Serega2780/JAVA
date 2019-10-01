package springhibernatemysql.controllers.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.PopulateCountries;

@RestController

public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private PopulateCountries populateCountries;

    public AdminController(UserService userService, RoleService roleService,
                           PopulateCountries populateCountries) {

        this.roleService = roleService;
        this.userService = userService;
        this.populateCountries = populateCountries;
    }

    //REST
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@RequestParam("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/countries", method = RequestMethod.GET)
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }


    @RequestMapping(value = "/admin/insert", method = RequestMethod.PUT)
    @ResponseBody
    public void addUpdateUser(@RequestBody User user) {
        userService.createUser(user);
    }

}
