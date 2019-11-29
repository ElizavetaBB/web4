package lab4.demo.controller;

import lab4.demo.entity.User;
import lab4.demo.service.UserDetailsServiceImpl;
import lab4.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
@RequestMapping
public class UserController {


    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @CrossOrigin
    @PostMapping("/api/register")
    public ResponseEntity<?> createUser(@RequestBody LinkedHashMap newUser) {
        String username=(String) newUser.get("username");
        if (userService.findByUsername(username) != null) {
            logger.error("User with username " + username + "already exist");
            return new ResponseEntity<>("User with username " + username + "already exist", HttpStatus.CONFLICT);
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword((String)newUser.get("password"));
        return new ResponseEntity<>(
                userService.save(user),
                HttpStatus.CREATED);
    }

    /*@CrossOrigin
    @GetMapping("/api/register")
    public HttpStatus creatUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        if (userService.findByUsername(username) != null) {
            return HttpStatus.CONFLICT;
        }
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        return
                HttpStatus.CREATED;
    }*/

    /*
    @CrossOrigin
    @RequestMapping("/api/logout")
    public ResponseEntity<?> logoutUser(){
    }*/
}