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

    /*@CrossOrigin
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
    }*/

    @CrossOrigin
    @PostMapping("/api/register")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (newUser==null || newUser.getUsername()==null || newUser.getPassword()==null || newUser.getUsername().trim().equals("") || newUser.getPassword().trim().equals("")){
            logger.error("null user");
            throw new ResourceException(HttpStatus.FORBIDDEN, "Null username or password");
        }
        if (userService.findByUsername(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity<>("User with username " + newUser.getUsername() + "already exist", HttpStatus.CONFLICT);
        }
        logger.info("user registered " + newUser.getUsername());
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }


    @CrossOrigin
    @RequestMapping("/api/logins")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }

}