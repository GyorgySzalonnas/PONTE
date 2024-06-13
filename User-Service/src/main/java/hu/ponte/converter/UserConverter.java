package hu.ponte.converter;

import hu.ponte.dto.UserDTO;
import hu.ponte.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverter {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PhoneNumberConverter phoneNumberConverter;

    public UserDTO toUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAddresses(user.getAddresses().stream().map(address -> addressConverter.toAddressDTO(address)).collect(Collectors.toSet()));
        dto.setPhoneNumbers(user.getPhoneNumbers().stream().map(number -> phoneNumberConverter.toPhoneNumberDTO(number)).collect(Collectors.toSet()));
        return dto;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddresses(userDTO.getAddresses().stream().map(addressDTO -> addressConverter.toAddress(addressDTO)).collect(Collectors.toSet()));
        user.setPhoneNumbers(userDTO.getPhoneNumbers().stream().map(phoneNumberDTO -> phoneNumberConverter.toPhoneNumber(phoneNumberDTO)).collect(Collectors.toSet()));
        return user;
    }
}
