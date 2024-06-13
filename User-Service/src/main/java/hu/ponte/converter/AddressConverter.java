package hu.ponte.converter;

import hu.ponte.dto.AddressDTO;
import hu.ponte.model.Address;
import org.springframework.stereotype.Service;

/**
 * Class to convert to and from Address and AddressDTO
 */
@Service
public class AddressConverter implements IConverter<Address, AddressDTO> {

    @Override
    public AddressDTO toDTO(Address address){
        AddressDTO dto = new AddressDTO();
        dto.setAddressName(address.getAddressName());
        dto.setCountry(address.getCountry());
        dto.setCity(address.getCity());
        dto.setCounty(address.getCounty());
        dto.setFlatNumber(address.getFlatNumber());
        dto.setFloorNumber(address.getFloorNumber());
        dto.setState(address.getState());
        dto.setStreet(address.getStreet());
        dto.setStreetNumber(address.getStreetNumber());
        dto.setZipCode(address.getZipCode());
        dto.setId(address.getId());
        return dto;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO){
        Address address = new Address();
        address.setAddressName(addressDTO.getAddressName());
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setCounty(addressDTO.getCounty());
        address.setFlatNumber(addressDTO.getFlatNumber());
        address.setFloorNumber(addressDTO.getFloorNumber());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setStreetNumber(addressDTO.getStreetNumber());
        address.setZipCode(addressDTO.getZipCode());
        return address;
    }
}
