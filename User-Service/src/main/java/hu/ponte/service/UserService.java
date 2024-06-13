package hu.ponte.service;

import hu.ponte.converter.AddressConverter;
import hu.ponte.converter.PhoneNumberConverter;
import hu.ponte.converter.UserConverter;
import hu.ponte.dto.UserDTO;
import hu.ponte.exception.InputValidationException;
import hu.ponte.model.Address;
import hu.ponte.model.PhoneNumber;
import hu.ponte.model.User;
import hu.ponte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to manage User related operations.
 */
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
        return userRepository.findAll().stream().map(user -> userConverter.toDTO(user)).collect(Collectors.toList());
    }

    /**
     * @param userDTO
     * @return
     *
     * Updates a User with the DTO-s non-null elements.
     */
    public UserDTO updatePerson(UserDTO userDTO){
        User user = userConverter.toEntity(this.findById(userDTO.getUserId()));
        if (user != null) {
            if(!userDTO.getFirstName().isBlank())
                user.setFirstName(userDTO.getFirstName());
            if(!userDTO.getLastName().isBlank())
                user.setLastName(userDTO.getLastName());
            if(!userDTO.getEmails().isEmpty())
                user.setEmails(userDTO.getEmails());
            if(userDTO.getAddresses() != null)
                user.setAddresses(userDTO.getAddresses().stream().map(addressDTO -> addressConverter.toEntity(addressDTO)).collect(Collectors.toSet()));
            if(userDTO.getPhoneNumbers() != null)
                user.setPhoneNumbers(userDTO.getPhoneNumbers().stream().map(numberDTO -> phoneNumberConverter.toEntity(numberDTO)).collect(Collectors.toSet()));
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        for(Address address : user.getAddresses())
            address.setUser(user);
        for(PhoneNumber phoneNumber : user.getPhoneNumbers())
            phoneNumber.setUser(user);
        return userConverter.toDTO(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(Long id) {
        return userConverter.toDTO(userRepository.findById(id).orElse(null));
    }

    public void addEmailForUser(long userId, String email) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            user.get().getEmails().add(email);
            userRepository.save(user.get());
        }
    }

    public void deleteEmailForUser(long userId, String email) throws InputValidationException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() &&
                !(user.get().getPhoneNumbers().isEmpty()
                        && user.get().getEmails().contains(email)
                        && user.get().getEmails().size() == 1)) {
            user.get().getEmails().remove(email);
            userRepository.save(user.get());
        }
        else{
            throw new InputValidationException("Cant delete email, as there are no phone numbers for the user, and this is their only email address!");
        }
    }
}