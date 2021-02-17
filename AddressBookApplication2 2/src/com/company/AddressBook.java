package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

        ArrayList<AddressEntry> elements = new ArrayList<>();
        public void add(AddressEntry item){
            elements.add(item);
        }
        public void list()
        {
            for(int i=0; i< elements.size();i++)
            {
                System.out.println(elements.get(i).toString());
            }
        }

        //create private constructor, method getInstance
}


