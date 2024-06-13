package hu.ponte.service;

import hu.ponte.converter.PhoneNumberConverter;
import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.model.User;
import hu.ponte.repository.PhoneNumberRepository;
import hu.ponte.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private PhoneNumberConverter phoneNumberConverter;

    public Set<PhoneNumberDTO> getAllForUser(long userId) {
        PhoneNumber findBy = new PhoneNumber();
        findBy.setUser(new User(userId));
        return phoneNumberRepository.findAll(Example.of(findBy)).stream().map(
                        address -> phoneNumberConverter.toDTO(address))
                .collect(Collectors.toSet());
    }

    public void deleteById(long id) {
        phoneNumberRepository.deleteById(id);
    }

    public PhoneNumberDTO add(PhoneNumberDTO phoneNumberDTO) {
        return phoneNumberConverter.toDTO(phoneNumberRepository.save(phoneNumberConverter.toEntity(phoneNumberDTO)));
    }
}