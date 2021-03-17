package address.data;

public class AddressEntry {
    private Integer id;
    private String phone;
    private String email;



    private  Name name;
    private Address address;



    public AddressEntry() {

    }

    public AddressEntry(int id,String firstName, String lastName, String street, String city, String state, Integer zip, String email,String phone) {
       this.id= id;
        name = new Name(firstName,lastName);
        address = new Address(street,city,state,zip);
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return
                id+
                " " + name.getFirstName() +
                " " + name.getLastName()+
                " " + address.getStreet() +
                " " + address.getCity()+
                " " + address.getState() +
                " " + address.getZip() +
                " " + phone + " " + email;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getLastName()
    {
        return name.getLastName();
    }
}
