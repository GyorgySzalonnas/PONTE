package hu.ponte.service;

import hu.ponte.converter.AddressConverter;
import hu.ponte.dto.AddressDTO;
import hu.ponte.model.Address;
import hu.ponte.model.User;
import hu.ponte.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class to manage Address related operations.
 */
@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressConverter addressConverter;

    public Set<AddressDTO> getAllForUser(long userId) {
        Address findBy = new Address();
        findBy.setUser(new User(userId));
        return addressRepository.findAll(Example.of(findBy)).stream().map(
                address -> addressConverter.toDTO(address))
                .collect(Collectors.toSet());
    }

    public void deleteById(long addressId) {
        addressRepository.deleteById(addressId);
    }

    public AddressDTO add(AddressDTO addressDTO) {
        return addressConverter.toDTO(addressRepository.save(addressConverter.toEntity(addressDTO)));
    }
}
