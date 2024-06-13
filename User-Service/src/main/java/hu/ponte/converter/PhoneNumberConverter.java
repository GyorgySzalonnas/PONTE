package hu.ponte.converter;

import hu.ponte.dto.PhoneNumberDTO;
import hu.ponte.model.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberConverter {

    public PhoneNumberDTO toPhoneNumberDTO(PhoneNumber phoneNumber){
        PhoneNumberDTO dto = new PhoneNumberDTO();
        dto.setId(phoneNumber.getId());
        dto.setCountryCode(phoneNumber.getCountryCode());
        dto.setAreaCode(phoneNumber.getAreaCode());
        dto.setPhoneNumber(phoneNumber.getPhoneNumber());
        return dto;
    }

    public PhoneNumber toPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCountryCode(phoneNumberDTO.getCountryCode());
        phoneNumber.setAreaCode(phoneNumberDTO.getAreaCode());
        phoneNumber.setPhoneNumber(phoneNumberDTO.getPhoneNumber());
        return phoneNumber;
    }
}
