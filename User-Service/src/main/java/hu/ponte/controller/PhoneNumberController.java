package hu.ponte.controller;

import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for PhoneNumber objects.
 */
@RestController
@RequestMapping("/api/phone")
public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    @GetMapping("/all/{userId}")
    public Set<PhoneNumberDTO> getAllForUser(long userId){
        return phoneNumberService.getAllForUser(userId);
    }

    @DeleteMapping("/{userId}/{phoneNumberId}")
    public void deleteForUser(long userId, long phoneNumberId){
        phoneNumberService.deleteForUser(userId, phoneNumberId);
    }

    @PutMapping("/add")
    public PhoneNumberDTO add(@RequestBody PhoneNumberDTO addressDTO){
        return phoneNumberService.add(addressDTO);
    }
}
