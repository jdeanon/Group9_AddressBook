import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AddressBookApplication reads the user input from the menu selection and
 * then it calls the methods for the proper menu selection and then it
 * processes Menu class and AddressBook class accordingly.
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
                    initAddressBookExercise();//adding data
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
        System.out.println("Enter File Name:");
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
        ArrayList<AddressEntry> results= ab.find(input.nextLine());
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
        System.out.println("Enter in all or the beginning of the Last Name of the contact you wish to find: ");
        Scanner input = new Scanner(System.in);
        String lastName = input.nextLine();
        ArrayList <AddressEntry> results = ab.find(lastName);
        System.out.print("The following entries were found in the address book for a last name starting with: " );
        System.out.println(results.toString());
        for(int i =0; i< results.size();i++)
        {
            System.out.println(i+1);
            System.out.println(results.get(i).toString());
        }

    }

    /**
     * The initAddressBookExercise() method recieves the input from user by using the keyboard.
     * @throws IOException
     */
    public static void initAddressBookExercise() throws IOException {

        //Getting the input from user
        Scanner keyboard = new Scanner(System.in);
        AddressEntry item2 = new AddressEntry();
        item2.setFirstName(Menu.prompt_FirstName(System.in));
        item2.setLastName(Menu.prompt_LastName(System.in));
        item2.setStreet(Menu.prompt_Street(System.in));
        item2.setCity(Menu.prompt_City(System.in));
        item2.setState(Menu.prompt_State(System.in));
        item2.setZip(Menu.prompt_Zip(System.in));
        item2.setPhone(Menu.prompt_Telephone(System.in));
        item2.setEmail(Menu.prompt_Email(System.in));
        ab.add(item2);
        System.out.println("\nThank you the following contact has been added to your address book:");
        System.out.println(item2.getFirstName());
        System.out.println(item2.getLastName());
        System.out.println(item2.getStreet());
        System.out.println(item2.getCity());
        System.out.println(item2.getState());
        System.out.println(item2.getZip());
        System.out.println(item2.getPhone());
        System.out.println(item2.getEmail());


    }

    /**
     *The init() method Reads and parses the data from AddressEntry.java file until the end of file.
     * @param filename
     * @throws IOException
     */
    public static void init(String filename) throws IOException
    {
        FileReader file_reader = new FileReader(filename);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        String Current_line, f_name,l_name,street,city,state,telephone,email;
        int zip;
        while ((Current_line = buffered_reader.readLine()) != null)
        {
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
            AddressEntry elements = new AddressEntry(f_name,l_name,street,city,state,zip,email,telephone);
            ab.add(elements);
        }


    }

}
