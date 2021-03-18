package address;

import address.data.AddressEntry;

import java.util.Comparator;
import java.util.Vector;

/**
 * The address.AddressBook class contains AddressEntries
 *
 * @author Sitara Meherzad
 * @since 03-16-2021
 *
 */
public class AddressBook {

    Vector<AddressEntry> elements = new Vector<>();

    /**
     * adds a new AddressEntry item to the AddressBook
     * @param item
     */
    public void add(AddressEntry item){
        elements.add(item);
    }

    /**
     * lists all the AddressEntry elements in the AddressBook
     */
    public void list()
    {
        elements.sort(Comparator.comparing(AddressEntry::getLastName));
        for(int i=0; i< elements.size();i++)
        {
            System.out.println(elements.get(i).toString());
        }
    }

    /**
     * removes an AddressEntry from the AddressBook
     * @param entry
     */
    public void remove(AddressEntry entry)
    {
        elements.remove(entry);

    }

    /**
     * gets elements of Vector containging AddressEntries
     * @return elements of the Vector
     */
    public Vector<AddressEntry> getList()
    {
        return elements;
    }

    /**
     * finds AddressEntry(s) in Vector using lastName
     * @param lastName
     * @return found results
     */
    public Vector<AddressEntry> find(String lastName)
    {
        Vector<AddressEntry> results = new Vector<>();
        for(int i = 0; i < elements.size();i++)
        {
            if(elements.get(i).getLastName().toLowerCase().startsWith(lastName.toLowerCase()))
            {
                results.add(elements.get(i));
            }

        }
        System.out.println(results);
        return results;
    }
}


