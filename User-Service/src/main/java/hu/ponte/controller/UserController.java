package hu.ponte.controller;

import hu.ponte.dto.UserDTO;
import hu.ponte.exception.InputValidationException;
import hu.ponte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws InputValidationException {
        if(userDTO.getEmails().isEmpty() && userDTO.getPhoneNumbers().isEmpty())
            throw new InputValidationException("Emails and phone numbers cant be both null!");
        return userService.save(userDTO);
    }

    @PutMapping("/update")
    public UserDTO updatePerson(@RequestBody UserDTO userDTO) throws InputValidationException {
        if(userDTO.getEmails().isEmpty() && userDTO.getPhoneNumbers().isEmpty())
            throw new InputValidationException("Emails and phone numbers cant be both null!");
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        userService.deleteById(id);
    }
}