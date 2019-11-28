package application.controller;
import application.dao.UserDAO;
import application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserDAO userService;

    @CrossOrigin
    @PostMapping("api/register")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getUsername()) != null) {
            System.out.println("username Already exist " + newUser.getUsername());
            return new ResponseEntity<>(
                    new RuntimeException("User with username " + newUser.getUsername() + "already exist"),
                    HttpStatus.CONFLICT);
        }
        System.out.println("user registered " + newUser.getUsername());
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("api/register")
    public ResponseEntity<?> createUser(@RequestParam(value = "j_username") String username, @RequestParam(value = "j_password") String password) {
        if (userService.findByUsername(username) != null) {
            System.out.println("username Already exist " + username);
            return new ResponseEntity<>(
                    new RuntimeException("User with username " + username + "already exist"),
                    HttpStatus.CONFLICT);
        }
        System.out.println("user registered " + username);
        return new ResponseEntity<>(userService.save(new User(username,password)), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        System.out.println("user logged "+principal);
        return principal;
    }

}
