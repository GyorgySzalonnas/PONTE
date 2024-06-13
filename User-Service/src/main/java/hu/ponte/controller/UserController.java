package hu.ponte.controller;

import hu.ponte.dto.UserDTO;
import hu.ponte.exception.InputValidationException;
import hu.ponte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for User object.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getById(long id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllPersons() {
        return userService.findAll();
    }

    /**
     *
     * @param userDTO
     * @return
     * @throws InputValidationException
     *
     * Creates a User object, if it has at least one email address or phone number.
     */
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws InputValidationException {
        if(userDTO.getEmails().isEmpty() && userDTO.getPhoneNumbers().isEmpty())
            throw new InputValidationException("Emails and phone numbers cant be both null!");
        return userService.save(userDTO);
    }

    /**
     *
     * @param userDTO
     * @return
     * @throws InputValidationException
     *
     * Updates a User object, if it has at least one email address or phone number.
     */
    @PutMapping("/update")
    public UserDTO updatePerson(@RequestBody UserDTO userDTO) throws InputValidationException {
        if(userDTO.getEmails().isEmpty() && userDTO.getPhoneNumbers().isEmpty())
            throw new InputValidationException("Emails and phone numbers cant be both null!");
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(long id) {
        userService.deleteById(id);
    }
}