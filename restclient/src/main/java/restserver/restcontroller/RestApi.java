package restserver.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restclient.domain.User;
import restclient.service.UserService;
import restclient.service.implementation.PopulateCountries;
import restclient.service.implementation.UserServiceImpl;

@RestController
@RequestMapping(value = {
        "/restapi/**"
})
public class RestApi {
    @Autowired
    private UserService userService;


    public RestApi(UserService userService) {

        this.userService = userService;

    }

    @RequestMapping(value = "/allusers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsersList() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getuser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<User> getUsersById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
