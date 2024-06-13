package hu.ponte.model;

import jakarta.persistence.*;

/**
 * Class that represents an address of a user.
 */
@Entity
@Table(name = "address")
public class Address {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String addressName;

    @Column(nullable = false, length = 25)
    private String country;

    @Column(length = 25)
    private String state;

    @Column(length = 25)
    private String county;

    @Column(nullable = false, length = 25)
    private String city;

    @Column(nullable = false, length = 25)
    private String zipCode;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 25)
    private String streetNumber;

    @Column
    private Integer floorNumber;

    @Column
    private Integer flatNumber;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return addressName + ": " + country + ", " + state + ", " + county + ", " + city + ", " +
                zipCode + ", " + street + ", " + streetNumber + ", " + floorNumber+ "/" + flatNumber;
    }
}
