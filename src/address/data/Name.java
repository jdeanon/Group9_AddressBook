package address.data;
/**
 * This class represents a single Name from the AddressBook
 *
 * @author Sitara Meherzad
 * @since 03-16-2021
 */
public class Name {

    // class instance variables representing a Name
    private String firstName;
    private String lastName;

    /**
     * Name constructor with 2 parameters
     * @param firstName
     * @param lastName
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters and setters
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
}
