package hu.ponte.controller;

import hu.ponte.dto.UserDTO;
import hu.ponte.model.Address;
import hu.ponte.model.PhoneNumber;
import hu.ponte.model.User;
import hu.ponte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getById(Long id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllPersons() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/update")
    public UserDTO updatePerson(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        userService.deleteById(id);
    }
}