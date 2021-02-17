package com.company;
import java.io.*;
import java.io.File;
import java.util.Scanner;

public class AddressBookApplication {

    public static void main(String args[]) throws IOException {

        //simply invokes static methods of the Menu class
        AddressBook ab = new AddressBook();
        init("/Users/sitara/desktop/AddressBookApplication2/src/com/company/AddressInputDataFile.txt",ab);
        ab.list();



    }
    public static void initAddressBookExercise(AddressBook ab) throws IOException {
        //Setting up the input
        AddressEntry item = new AddressEntry("John", "Jackson", "1234 Johnson St", "JacksonVille", "JK", 95342, "(123)344-5333", "john_jackson@gmail.com");

        //Getting the input from user
        Scanner keyboard = new Scanner(System.in);
        AddressEntry item2 = new AddressEntry();
        Menu.prompt_FirstName();
        item2.setFirstName(keyboard.nextLine());
        Menu.prompt_LastName();
        item2.setLastName(keyboard.nextLine());
        Menu.prompt_Street();
        item2.setStreet(keyboard.nextLine());
        Menu.prompt_City();
        item2.setCity(keyboard.nextLine());
        Menu.prompt_State();
        item2.setState(keyboard.nextLine());
        Menu.prompt_Zip();
        item2.setZip(keyboard.nextInt());
        Menu.prompt_Telephone();
        item2.setPhone(keyboard.nextLine());
        Menu.prompt_Email();
        item2.setEmail(keyboard.nextLine());

        ab.add(item);
        ab.list();
    }
        public static void init(String filename, AddressBook ab) throws IOException
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
                Current_line = buffered_reader.readLine();

                AddressEntry elements = new AddressEntry(f_name,l_name,street,city,state,zip,email,telephone);
                ab.add(elements);

            }


        }

    }



