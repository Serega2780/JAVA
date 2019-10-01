package restclient.controllers.resttemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restclient.service.RoleService;

@RestController
public class RolesController {

    private RoleService roleService;


    public RolesController(RoleService roleService) {

        this.roleService = roleService;

    }

    @RequestMapping(value = "/admin/roles")
    public ResponseEntity<Object> getRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role")
    public ResponseEntity<Object> getRole(@RequestParam("role") String roleName) {
        return new ResponseEntity<>(roleService.getSingleRoleByName(roleName), HttpStatus.OK);
    }
}
