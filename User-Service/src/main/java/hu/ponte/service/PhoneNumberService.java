package hu.ponte.service;

import hu.ponte.repository.PhoneNumberRepository;
import hu.ponte.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumber> findAll() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber save(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public void deleteById(Long id) {
        phoneNumberRepository.deleteById(id);
    }

    public PhoneNumber findById(Long id) {
        return phoneNumberRepository.findById(id).orElse(null);
    }
}