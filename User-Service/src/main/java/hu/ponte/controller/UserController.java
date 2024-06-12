package hu.ponte.controller;

import hu.ponte.model.User;
import hu.ponte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllPersons() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public User createPerson(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updatePerson(@PathVariable Long id, @RequestBody User personDetails) {
        User user = userService.findById(id);
        if (user != null) {
            user.setFirstName(personDetails.getFirstName());
            user.setLastName(personDetails.getLastName());
            user.setAddresses(personDetails.getAddresses());
            user.setPhoneNumbers(personDetails.getPhoneNumbers());
            return userService.save(user);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        userService.deleteById(id);
    }
}