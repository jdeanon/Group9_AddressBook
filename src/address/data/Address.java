package address.data;
/**
 * @author Sitara Meherzad, Mamata Polisetty, Joshua Deanon
 * @since 03-16-2021
 *
 * This class represents a single Address information entry in the AddressBook
 *
 */
public class Address {

    // class instance variables representing an address
    private String street;
    private String city;
    private String state;
    private Integer zip;

    /**
     * address constructor with 4 parameters
     * @param street
     * @param city
     * @param state
     * @param zip
     */
    public Address(String street, String city, String state, Integer zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    // getters and setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
