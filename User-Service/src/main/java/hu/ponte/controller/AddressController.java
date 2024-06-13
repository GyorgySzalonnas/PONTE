package hu.ponte.controller;

import hu.ponte.dto.AddressDTO;
import hu.ponte.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AddressController {
    @Autowired
    AddressService addressService;

    public Set<AddressDTO> getAllForUser(long userId){
        return addressService.getAllForUser(userId);
    }

    public void deleteById(long id){
        addressService.deleteById(id);
    }

    public AddressDTO add(AddressDTO addressDTO){
        return addressService.add(addressDTO);
    }
}
