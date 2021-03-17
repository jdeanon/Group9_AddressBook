package address;

import address.data.AddressEntry;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * The address.AddressBookApplication reads the user input from the menu selection and
 * then it calls the methods for the proper menu selection and then it
 * processes address.Menu class and address.AddressBook class accordingly.
 */
public class AddressBookApplication {

    public static AddressBook ab = new AddressBook();//set as a Global variable

    public static void main(String args[]) throws IOException {

        char selection;


        selection = Menu.promptMenu();
        while(selection != 'f')
        {
            switch(selection)
            {
                case 'a':
                    loadFromFile();//Loading data from file
                    break;
                case 'b':
                    break;
                case 'c':
                    removal();//removing data
                    break;
                case 'd':
                    findLastName();//finding data through the last name
                    break;
                case 'e':
                    ab.list();//Displaying the list or data
                    break;
                default:
                    System.out.println("Invalid Entry");
                    break;
            }
            selection = Menu.promptMenu();
            if(selection == 'f')
            {
                System.out.println("Goodbye");//Quitting
            }
        }

    }

    /**
     * The ladFromFile() method prompts the user to enter a file name
     * then it reads the entry and gets the elements for the init() method.
     * @throws IOException
     */
    public static void loadFromFile() throws IOException {
        System.out.println("Enter File address.data.Name:");
        Scanner fileNameInput = new Scanner(System.in);
        init(fileNameInput.nextLine());

    }

    /**
     * The removal() method reads user's entry for last name in the file and then
     * it looks for the last name in the file by looking at the elements in the list
     * and then it removes the last name by user entering 'y' for yes. Or if the user
     * hits 'n' for no then it will be directed back to the menu.
     */
    public static void removal()
    {
        System.out.println("Enter last name for removal:");
        Scanner input = new Scanner(System.in);
        Vector<AddressEntry> results= ab.find(input.nextLine());
        for(int i =0; i< results.size();i++)
        {
            System.out.println(i+1);

            System.out.println(results.get(i).toString());
        }
        System.out.print("The Following entries have been found, select number of entry you wish to remove:");
        int selection =  input.nextInt();

        System.out.print("Hit y to remove the following entry or n to return to main menu:");
        System.out.println(results.get(selection-1).toString());
        System.out.print(">");
        char remove;
        remove = input.next().charAt(0);
        if(remove =='y')
        {
            ab.remove(results.get(selection-1));
        }
        else if(remove == 'n')
        {
            return;
        }


    }

    /**
     * The findLastName() method reads user's input for last name and looks for the last name
     * and displays those entries.
     */
    public static void findLastName()
    {
        System.out.println("Enter in all or the beginning of the Last address.data.Name of the contact you wish to find: ");
        Scanner input = new Scanner(System.in);
        String lastName = input.nextLine();
        Vector<AddressEntry> results = ab.find(lastName);
        System.out.print("The following entries were found in the address book for a last name starting with: " );
        System.out.println(results.toString());
        for(int i =0; i< results.size();i++)
        {
            System.out.println(i+1);
            System.out.println(results.get(i).toString());
        }

    }


    /**
     *The init() method Reads and parses the data from address.data.AddressEntry.java file until the end of file.
     * @param filename
     * @throws IOException
     */
    public static void init(String filename) throws IOException
    {
        FileReader file_reader = new FileReader(filename);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        String Current_line, f_name,l_name,street,city,state,telephone,email;
        int zip,id;
        while ((Current_line = buffered_reader.readLine()) != null)
        {
            id = Integer.valueOf(Current_line);
            Current_line =buffered_reader.readLine();
            f_name = Current_line;
            Current_line = buffered_reader.readLine();
            l_name = Current_line;
            Current_line =buffered_reader.readLine();
            street = Current_line;
            Current_line = buffered_reader.readLine();
            city = Current_line;
            Current_line = buffered_reader.readLine();
            state = Current_line;
            Current_line = buffered_reader.readLine();
            zip = Integer.valueOf(Current_line);
            Current_line =buffered_reader.readLine();
            email= Current_line;
            Current_line = buffered_reader.readLine();
            telephone = Current_line;
            AddressEntry elements = new AddressEntry(id,f_name,l_name,street,city,state,zip,email,telephone);
            ab.add(elements);
        }


    }

}
