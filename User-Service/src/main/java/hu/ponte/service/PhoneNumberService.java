package hu.ponte.service;

import hu.ponte.converter.PhoneNumberConverter;
import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.dto.UserDTO;
import hu.ponte.model.User;
import hu.ponte.repository.PhoneNumberRepository;
import hu.ponte.model.PhoneNumber;
import hu.ponte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class to manage PhoneNumber related operations.
 */
@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private PhoneNumberConverter phoneNumberConverter;

    @Autowired
    private UserRepository userRepository;

    public Set<PhoneNumberDTO> getAllForUser(long userId) {
        PhoneNumber findBy = new PhoneNumber();
        findBy.setUser(new User(userId));
        return phoneNumberRepository.findAll(Example.of(findBy)).stream().map(
                        address -> phoneNumberConverter.toDTO(address))
                .collect(Collectors.toSet());
    }

    public PhoneNumberDTO add(PhoneNumberDTO phoneNumberDTO) {
        return phoneNumberConverter.toDTO(phoneNumberRepository.save(phoneNumberConverter.toEntity(phoneNumberDTO)));
    }

    public void deleteForUser(long userId, long phoneNumberId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
            return;
        User user = userOptional.get();
        Optional<PhoneNumber> phoneNumber = phoneNumberRepository.findById(phoneNumberId);
        if(!phoneNumber.isPresent())
            return;
        if(!(user.getEmails().isEmpty()
                && user.getPhoneNumbers().contains(phoneNumber.get())
                && user.getPhoneNumbers().size() == 1)){
            user.getPhoneNumbers().remove(phoneNumber);
            userRepository.save(user);
        }
    }
}