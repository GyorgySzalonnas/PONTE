package hu.ponte.controller;

import hu.ponte.exception.InputValidationException;
import hu.ponte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    UserService userService;

    @GetMapping("/all/{userId}")
    public Set<String> getAllForUser(@PathVariable long userId){
        return userService.findById(userId).getEmails();
    }

    @PutMapping("/add/{userId}")
    public void addForUser(@PathVariable long userId, @RequestBody String email){
        userService.addEmailForUser(userId, email);
    }

    @DeleteMapping("/{userId}")
    public void deleteForUser(@PathVariable long userId, @RequestBody String email) throws InputValidationException {
        userService.deleteEmailForUser(userId, email);
    }
}
