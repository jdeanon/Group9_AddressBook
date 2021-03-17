package address;

import address.data.AddressEntry;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookGUI {

    private JFrame frame;
    private JButton display;
    private JButton newButton;
    private JButton addInfo;
    private JButton update;
    private JTextField fname = new JTextField(20);
    private JTextField lname = new JTextField(20);
    private JTextField stname = new JTextField(20);
    private JTextField cname = new JTextField(20);
    private JTextField sname = new JTextField(20);
    private JTextField zip = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField tele = new JTextField(20);
    private JLabel f_name = new JLabel("First Name:");
    private JLabel l_name = new JLabel("Last Name:");
    private JLabel st_name = new JLabel("Street address:");
    private JLabel c_name = new JLabel("City:");
    private JLabel s_name = new JLabel("State:");
    private JLabel zip_num = new JLabel("Zip code:");
    private JLabel telephone = new JLabel("Phone Number:");
    private JLabel email_address = new JLabel("Email address:");
    String fname_input,lname_input,stname_input,cname_input,sname_input,ename_input,tnum_input;
    int zip_input;

    FindEntryGUI finder = new FindEntryGUI();

    static AddressBook addressEntryList = new AddressBook();

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

    public AddressBookGUI() throws IOException, SQLException, ClassNotFoundException {
        databaseReading();

        for(int i = 0; i<addressEntryList.elements.size(); i++)
        {  this.myaddressEntryListModel.add(i, this.addressEntryList.elements.elementAt(i)); }



        //Now when we create our JList do it from our ListModel rather than our vector of address.data.AddressEntry

        addressEntryJList = new JList<AddressEntry>(this.myaddressEntryListModel);

        //setting up the look of the JList

        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        this.addressEntryJList.setVisibleRowCount(-1);

        //setup GUI and use the JList we created

        initialize();


    }

    public void databaseReading() throws IOException, ClassNotFoundException, SQLException {
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions

        //check Oracle documentation online
        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());



        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
        String username,password;
        File file = new File("credentials.txt");
        Scanner input = new Scanner(file);
        username = input.nextLine();
        password = input.nextLine();
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement ();



        // Select the all (*) from the table JAVATEST

        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

        System.out.println(rset);





        // Iterate through the result and print the employee names

        while(rset.next()) //get next row of table returned

        {
            ArrayList<String> elements = new ArrayList();
            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++)
            { //visit each column

                elements.add(rset.getString(i));
            }

            AddressEntry new_elements = new AddressEntry(Integer.parseInt(elements.get(0)),elements.get(1),elements.get(2),elements.get(3),
                                                                elements.get(4),elements.get(5),Integer.parseInt(elements.get(6)),elements.get(7),elements.get(8));

            addressEntryList.add(new_elements);
        }

        //Close access to everything...will otherwise happen when disconnect

        // from database.

        rset.close();

        stmt.close();

        conn.close();

    }
    /**

     * Initialize the contents of the frame.

     */

    private void initialize() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel();
        Box panel3 = Box.createVerticalBox();
        JDialog dialog = new JDialog();
        dialog.setBounds(132,132,300,500);
        frame = new JFrame();
        frame.setLayout(new BorderLayout(20,20));
        frame.setSize(960,600);
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
        addInfo = new JButton("Add");
        update = new JButton("Update");


        frame.add(finder.findPanel,BorderLayout.SOUTH);
        panel.add(display);
        panel.add(newButton);
        panel.add(update);
        panel.add(btnRemove);
        panel.add(panel2);
        frame.add(panel,BorderLayout.CENTER);
        panel3.add(f_name);
       panel3.add(fname);
       panel3.add(l_name);
       panel3.add(lname);
       panel3.add(st_name);
       panel3.add(stname);
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
       panel3.add(addInfo);
       dialog.add(panel3);


        btnRemove.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,
            //Remove item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0) {

                int index = addressEntryJList.getSelectedIndex();
                AddressEntry new_instance =  addressEntryList.elements.get(index);


                if(index != -1)//something is selected otherwise do nothing

                {   //retrieve the DeffaultListModel associated
                    // with our JList and remove from it the address.data.AddressEntry at this index

                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
                    try {
                        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions


                        //check Oracle documentation online
                        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

                        // Connect to the database
                        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
                        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
                        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
                        String username,password;
                        File file = new File("credentials.txt");
                        Scanner input = new Scanner(file);
                        username = input.nextLine();
                        password = input.nextLine();
                        Connection conn =
                                DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

                        // Create a Statement
                        Statement stmt = conn.createStatement ();
                        stmt.executeUpdate("DELETE FROM ADDRESSENTRYTABLE WHERE ID=" + new_instance.getId());                        System.out.println(stmt);
                        stmt.close();
                        conn.close();
                        addressEntryList.remove(new_instance);
                        myaddressEntryListModel.removeAllElements();
                        myaddressEntryListModel.addAll(addressEntryList.elements);
                    } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // NOTE in your project 2 you will also remove it from your address.AddressBook.addressEntryList
                    // AND ALSO remove it from the associated database table

                }

            }

        });
        update.addActionListener(new ActionListener() {  //BASED ON event from hitting remove button,
            //Remove item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0) {

                int index = addressEntryJList.getSelectedIndex();
                AddressEntry new_instance =  addressEntryList.elements.get(index);


                if(index != -1)//something is selected otherwise do nothing

                {   //retrieve the DefaultListModel associated
                    // with our JList and remove from it the address.data.AddressEntry at this index

                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
                    try {

                        fname.setText(new_instance.getName().getFirstName());
                        lname.setText(new_instance.getName().getLastName());
                        stname.setText(new_instance.getAddress().getStreet());
                        cname.setText(new_instance.getAddress().getCity());
                        sname.setText(new_instance.getAddress().getState());
                        zip.setText(Integer.toString(new_instance.getAddress().getZip()));
                        email.setText(new_instance.getEmail());
                        tele.setText(new_instance.getPhone());

                        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions


                        //check Oracle documentation online
                        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

                        // Connect to the database
                        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
                        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
                        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
                        String username,password;
                        File file = new File("credentials.txt");
                        Scanner input = new Scanner(file);
                        username = input.nextLine();
                        password = input.nextLine();
                        Connection conn =
                                DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

                        // Create a Statement
                        Statement stmt = conn.createStatement ();
                        stmt.executeUpdate("DELETE FROM ADDRESSENTRYTABLE WHERE ID=" + new_instance.getId());                        System.out.println(stmt);
                        stmt.close();
                        conn.close();
                        addressEntryList.remove(new_instance);
                        myaddressEntryListModel.removeAllElements();
                        myaddressEntryListModel.addAll(addressEntryList.elements);
                        dialog.setVisible(true);
                    } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // NOTE in your project 2 you will also remove it from your address.AddressBook.addressEntryList
                    // AND ALSO remove it from the associated database table

                }

            }

        });
        display.addActionListener(new ActionListener() {  //BASED ON event from hitting display button,
            //Display item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0) {

             finder.findPanel.setVisible(true);
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
        addInfo.addActionListener(new ActionListener() {  //BASED ON event from hitting new button,
            //Add item from our JList's ListModel

            public void actionPerformed(ActionEvent arg0)
            {
                try {
                    AddressEntry new_instance = new AddressEntry(addressEntryList.elements.size()+1,fname_input,lname_input,stname_input,cname_input,
                                                                    sname_input,zip_input,ename_input,tnum_input);
                    Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions


                //check Oracle documentation online
                // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());

                // Connect to the database
                // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
                // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
                //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
                    String username,password;
                    File file = new File("credentials.txt");
                    Scanner input = new Scanner(file);
                    username = input.nextLine();
                    password = input.nextLine();
                    Connection conn =
                            DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

                    // Create a Statement
                Statement stmt = conn.createStatement ();
                    stmt.executeUpdate(new StringBuilder().append("INSERT INTO ADDRESSENTRYTABLE ").append("VALUES(").append(new_instance.getId().toString()).append(", '").append(new_instance.getName().getFirstName()).append("', '").append(new_instance.getName().getLastName()).append("', '").append(new_instance.getAddress().getStreet()).append("', '").append(new_instance.getAddress().getCity()).append("', '").append(new_instance.getAddress().getState()).append("',").append(new_instance.getAddress().getZip().toString()).append(",'").append(new_instance.getPhone()).append("', '").append(new_instance.getEmail()).append("')").toString());
                System.out.println(stmt);
                stmt.close();

                conn.close();
                dialog.setVisible(false);
                addressEntryList.add(new_instance);
                myaddressEntryListModel.removeAllElements();
                myaddressEntryListModel.addAll(addressEntryList.elements);
                } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                    e.printStackTrace();
                }



            }

        });

        fname.getDocument().addDocumentListener(new DocumentListener() {
                                                                @Override
                                                                public void insertUpdate(DocumentEvent e) {
                                                                    fname_input = fname.getText();
                                                                }

                                                                @Override
                                                                public void removeUpdate(DocumentEvent e) {
                                                                    fname_input = fname.getText();
                                                                }

                                                                @Override
                                                                public void changedUpdate(DocumentEvent e) {
                                                                    fname_input = fname.getText();


                                                                }
                                                            }

        );
        lname.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        lname_input = lname.getText();

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        lname_input = lname.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        lname_input = lname.getText();


                                                    }
                                                }

        );
        stname.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        stname_input = stname.getText();

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        stname_input = stname.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        stname_input = stname.getText();


                                                    }
                                                }

        );
        cname.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        cname_input = cname.getText();


                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        cname_input = cname.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        cname_input = cname.getText();


                                                    }
                                                }

        );
        sname.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        sname_input = sname.getText();

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        sname_input = sname.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        sname_input = sname.getText();


                                                    }
                                                }

        );
        zip.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        zip_input = Integer.parseInt(zip.getText());

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        zip_input = Integer.parseInt(zip.getText());


                                                    }
                                                }

        );
        email.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        ename_input = email.getText();

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        ename_input = email.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        ename_input = email.getText();


                                                    }
                                                }

        );
        tele.getDocument().addDocumentListener(new DocumentListener() {
                                                    @Override
                                                    public void insertUpdate(DocumentEvent e) {
                                                        tnum_input = tele.getText();

                                                    }

                                                    @Override
                                                    public void removeUpdate(DocumentEvent e) {
                                                        tnum_input = tele.getText();

                                                    }

                                                    @Override
                                                    public void changedUpdate(DocumentEvent e) {
                                                        tnum_input = tele.getText();


                                                    }
                                                }

        );
    }

}

