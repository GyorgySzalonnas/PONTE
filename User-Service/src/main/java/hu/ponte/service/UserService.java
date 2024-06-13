package hu.ponte.service;

import hu.ponte.converter.AddressConverter;
import hu.ponte.converter.PhoneNumberConverter;
import hu.ponte.converter.UserConverter;
import hu.ponte.dto.AddressDTO;
import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.dto.UserDTO;
import hu.ponte.model.Address;
import hu.ponte.model.PhoneNumber;
import hu.ponte.model.User;
import hu.ponte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PhoneNumberConverter phoneNumberConverter;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> userConverter.toUserDTO(user)).collect(Collectors.toList());
    }

    public UserDTO updatePerson(UserDTO userDTO){
        User user = userConverter.toUser(this.findById(userDTO.getUserId()));
        if (user != null) {
            if(!userDTO.getFirstName().isBlank())
                user.setFirstName(userDTO.getFirstName());
            if(!userDTO.getLastName().isBlank())
                user.setLastName(userDTO.getLastName());
            if(userDTO.getAddresses() != null)
                user.setAddresses(userDTO.getAddresses().stream().map(addressDTO -> addressConverter.toAddress(addressDTO)).collect(Collectors.toSet()));
            if(userDTO.getPhoneNumbers() != null)
                user.setPhoneNumbers(userDTO.getPhoneNumbers().stream().map(numberDTO -> phoneNumberConverter.toPhoneNumber(numberDTO)).collect(Collectors.toSet()));
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userConverter.toUser(userDTO);
        for(Address address : user.getAddresses())
            address.setUser(user);
        for(PhoneNumber phoneNumber : user.getPhoneNumbers())
            phoneNumber.setUser(user);
        return userConverter.toUserDTO(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(Long id) {
        return userConverter.toUserDTO(userRepository.findById(id).orElse(null));
    }
}