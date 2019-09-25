package springhibernatemysql.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.PopulateCountries;


import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RestController
@RequestMapping(value = {
        "/admin/**"
})
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

    @RequestMapping(value = "/admin/users")
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit")
    public ResponseEntity<Object> getUser(@RequestParam("id") int id) {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @RequestMapping(value = "/admin/countries")
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/roles")
    public ResponseEntity<Object> getRoles() {
        Set<Role> roles = roleService.getAllRoles();
        System.out.println(roles);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/admin/list")

    public ModelAndView home() {
        ModelAndView modelView = new ModelAndView("/user-list.html");
        //Set<Role> roles = roleService.getAllRoles();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // modelView.addObject("roles", roles);
        modelView.addObject("listUser", userService.getAllUsers());
       // modelView.addObject("countriesList", populateCountries.populateCountries());
        modelView.addObject("user", new User());
        modelView.addObject("currentUser", currentUser);
      //  System.out.println(userService.getAllUsers());
        return modelView;
    }

    @PutMapping("/admin/insert")
    @ResponseBody
    public void rrr(@RequestBody User user){
        int i=0;
        user.getName();
    }
    public ModelAndView addUser(@ModelAttribute User user, @RequestParam("roles") String roles) {
        Set<Role> grandAuthorities = new HashSet<>();
        Stream.of((roles + ",ROLE_USER").split(","))
                .map(elem -> new String(elem))
                .collect(Collectors.toList()).forEach(r -> grandAuthorities.add(roleService.getSingleRoleByName(r)));

        user.setGrantedAuthorities(grandAuthorities);
        userService.createUser(user);

        return new ModelAndView("redirect:/admin/list");
    }

    @GetMapping("/admin/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {

        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/list");

    }

}
