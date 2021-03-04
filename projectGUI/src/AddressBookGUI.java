
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.io.*;
import java.util.Vector;

public class AddressBookGUI {

    private JFrame frame;
    private JButton display;
    private JButton newButton;
    private JTextField fname = new JTextField(20);
    private JTextField lname = new JTextField(20);
    private JTextField cname = new JTextField(20);
    private JTextField sname = new JTextField(20);
    private JTextField zip = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField tele = new JTextField(20);
    private JLabel f_name = new JLabel("First Name:");
    private JLabel l_name = new JLabel("Last Name:");
    private JLabel c_name = new JLabel("City:");
    private JLabel s_name = new JLabel("State:");
    private JLabel zip_num = new JLabel("Zip code:");
    private JLabel telephone = new JLabel("Phone Number:");
    private JLabel email_address = new JLabel("Email Address:");


    Vector <AddressEntry> addressEntryList = new Vector<AddressEntry>();

    JList <AddressEntry> addressEntryJList;

    DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();

    /**

     * Launch the application.

     */

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    AddressBookGUI window = new AddressBookGUI();

                    window.frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

    }

    /**

     * Create the application.

     */

    public AddressBookGUI() throws IOException {
        fileReading();

        for(int i = 0; i<addressEntryList.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.elementAt(i)); }



        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry

        addressEntryJList = new JList<AddressEntry>(this.myaddressEntryListModel);

        //setting up the look of the JList

        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        this.addressEntryJList.setVisibleRowCount(-1);

        //setup GUI and use the JList we created

        initialize();


    }

    public void fileReading() throws IOException {

        FileReader file_reader = new FileReader("AddressInputDataFile.txt");
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
            addressEntryList.add(elements);

        }

    }
    /**

     * Initialize the contents of the frame.

     */

    private void initialize() {
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        Box panel3 = Box.createVerticalBox();
        JDialog dialog = new JDialog();
        dialog.setBounds(132,132,300,500);
        frame = new JFrame();

        frame.setBounds(100, 100, 450, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create scrollPane associated with JList

        JScrollPane scrollPane = new JScrollPane(this.addressEntryJList);
        scrollPane.setPreferredSize(new Dimension(600,200));

        panel2.add(scrollPane);
        panel2.setVisible(false);

        JButton btnRemove = new JButton("Remove");
        display = new JButton("Display");
        newButton = new JButton("New");

        panel.add(display);
        panel.add(newButton);
        panel.add(btnRemove);
        panel.add(panel2);
        frame.add(panel);
        panel3.add(f_name);
       panel3.add(fname);
       panel3.add(l_name);
       panel3.add(lname);
       panel3.add(c_name);
       panel3.add(cname);
       panel3.add(s_name);
       panel3.add(sname);
       panel3.add(zip_num);
       panel3.add(zip);
       panel3.add(email_address);
       panel3.add(email);
       panel3.add(telephone);
       panel3.add(tele);
       dialog.add(panel3);


        btnRemove.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,
            //Remove item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0) {

                int index = addressEntryJList.getSelectedIndex();

                if(index != -1)//something is selected otherwise do nothing

                {   //retrieve the DeffaultListModel associated
                    // with our JList and remove from it the AddressEntry at this index

                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);


                    // NOTE in your project 2 you will also remove it from your AddressBook.addressEntryList
                    // AND ALSO remove it from the associated database table

                }

            }

        });
        display.addActionListener(new ActionListener() {  //BASED ON event from hitting display button,
            //Display item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0) {

            panel2.setVisible(true);
            }

        });
        newButton.addActionListener(new ActionListener() {  //BASED ON event from hitting new button,
            //Add item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0)
            {

             dialog.setVisible(true);

            }

        });

    }

}

