package address;

import java.sql.*;

/**
 * This class creates an Oracle database connection and displays table
 * @author Lynne Grewe, Sitara Meherzad
 * @since 03-15-2021
 *
 *
 */
public class DataBaseConnect {

    /**
     * Main method
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{

        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions



        // Connect to the database
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1022/FSE8ZFGm@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement ();



        // Select the all (*) from the table ADDRESSENTRYTABLE
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");

        System.out.println(rset);


        // Iterate through the result and print the employee names

        while(rset.next()) //get next row of table returned

        {
            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++)
            { //visit each column

                System.out.print(rset.getString(i).trim() + " | ");
            }
            System.out.println(" ");

            System.out.println("========================================");


        }

        //Close access to everything...will otherwise happen when disconnect

        // from database.

        rset.close();

        stmt.close();

        conn.close();

    }

}