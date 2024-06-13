package hu.ponte.controller;

import hu.ponte.dto.AddressDTO;
import hu.ponte.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for Address objects.
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/all/{userId}")
    public Set<AddressDTO> getAllForUser( long userId){
        return addressService.getAllForUser(userId);
    }

    @DeleteMapping("/{phoneNumberId}")
    public void deleteById(long addressId){
        addressService.deleteById(addressId);
    }

    @PutMapping("/add")
    public AddressDTO add(@RequestBody AddressDTO addressDTO){
        return addressService.add(addressDTO);
    }
}
