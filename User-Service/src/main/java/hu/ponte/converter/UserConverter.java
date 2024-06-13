package hu.ponte.converter;

import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.dto.UserDTO;
import hu.ponte.model.PhoneNumber;
import hu.ponte.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverter  implements IConverter<User, UserDTO>{

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PhoneNumberConverter phoneNumberConverter;

    @Override
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAddresses(user.getAddresses().stream().map(address -> addressConverter.toDTO(address)).collect(Collectors.toSet()));
        dto.setPhoneNumbers(user.getPhoneNumbers().stream().map(number -> phoneNumberConverter.toDTO(number)).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddresses(userDTO.getAddresses().stream().map(addressDTO -> addressConverter.toEntity(addressDTO)).collect(Collectors.toSet()));
        user.setPhoneNumbers(userDTO.getPhoneNumbers().stream().map(phoneNumberDTO -> phoneNumberConverter.toEntity(phoneNumberDTO)).collect(Collectors.toSet()));
        return user;
    }
}
