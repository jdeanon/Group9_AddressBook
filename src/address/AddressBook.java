package address;

import address.data.AddressEntry;

import java.util.Comparator;
import java.util.Vector;

/**
 * The address.AddressBook class
 */
public class AddressBook {

    Vector<AddressEntry> elements = new Vector<>();
    public void add(AddressEntry item){
        elements.add(item);
    }
    public void list()
    {
        elements.sort(Comparator.comparing(AddressEntry::getLastName));
        for(int i=0; i< elements.size();i++)
        {
            System.out.println(elements.get(i).toString());
        }
    }

    public void remove(AddressEntry entry)
    {
        elements.remove(entry);

    }
    public Vector<AddressEntry> getList()
    {
        return elements;
    }
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


