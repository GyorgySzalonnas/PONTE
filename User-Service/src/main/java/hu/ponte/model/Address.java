package hu.ponte.model;

import javax.persistence.Entity;

@Entity
public class Address {

    int userId;
    String addressName;
    String country;
    String state;
    String county;
    String city;
    int zipCode;
    String street;
    String streetNumber;
    int floorNumber;
    int flatNumber;

}
