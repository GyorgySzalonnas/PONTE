package hu.ponte.controller;

import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    public Set<PhoneNumberDTO> getAllForUser(long userId){
        return phoneNumberService.getAllForUser(userId);
    }

    public void deleteById(long id){
        phoneNumberService.deleteById(id);
    }

    public PhoneNumberDTO add(PhoneNumberDTO addressDTO){
        return phoneNumberService.add(addressDTO);
    }
}
