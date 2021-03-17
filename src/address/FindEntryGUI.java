package address;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindEntryGUI {
    private JButton findButton;
    private JTextField findTextField;
    String findLastName;
    JPanel findPanel = new JPanel();
    JScrollPane findScroll;



    DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();

    JList <AddressEntry> addressEntryJList = new JList<>(myaddressEntryListModel);

    public FindEntryGUI() {
        findTextField = new JTextField(30);
        findPanel.add(findTextField);
        findTextField.getDocument().addDocumentListener(new DocumentListener() {
                                                   @Override
                                                   public void insertUpdate(DocumentEvent e) {
                                                       findLastName = findTextField.getText();

                                                   }

                                                   @Override
                                                   public void removeUpdate(DocumentEvent e) {
                                                       findLastName = findTextField.getText();

                                                   }

                                                   @Override
                                                   public void changedUpdate(DocumentEvent e) {
                                                       findLastName = findTextField.getText();

                                                   }
                                               }
        );
        findButton= new JButton("find");
        findPanel.add(findButton);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myaddressEntryListModel.addAll(AddressBookGUI.addressEntryList.find(findLastName));
                addressEntryJList.setModel(myaddressEntryListModel);
                addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

            }
        });

        findScroll = new JScrollPane(addressEntryJList);
        findScroll.setPreferredSize(new Dimension(600,250));
        findPanel.add(findScroll);
        findPanel.setVisible(false);

    }
}
