package hu.ponte.dto;

import java.util.HashSet;
import java.util.Set;


/**
 * User Data Transfer Object
 */
public class UserDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private Set<String> emails = new HashSet<>();

    private Set<AddressDTO> addresses = new HashSet<>();

    private Set<PhoneNumberDTO> phoneNumbers = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getEmails() { return emails; }

    public void setEmails(Set<String> emails) { this.emails = emails; }

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public Set<PhoneNumberDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
