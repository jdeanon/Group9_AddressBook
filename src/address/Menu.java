package address;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The address.Menu class displays the
 */
public class Menu {
    /**
     * prompt_FirstName  generates a standard output prompt for the First address.data.Name to be entered
     * @param in
     * @return
     */
    public static String prompt_FirstName(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("First address.data.Name:");
        return input.nextLine();
    }

    /**
     * prompt_LastName  generates a standard output prompt for the Last address.data.Name to be entered
     * @param in
     * @return
     */
    public static String prompt_LastName(InputStream in) {

        Scanner input = new Scanner(in);
        System.out.println("Last address.data.Name:");
        return input.nextLine();
    }

    /**
     * prompt_Street  generates a standard output prompt for the Street to be entered
     * @param in
     * @return
     */
    public static String prompt_Street(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("Street address.data.Address:");
        return input.nextLine();
    }

    /**
     * prompt_City  generates a standard output prompt for the City to be entered
     * @param in
     * @return
     */
    public static String prompt_City(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("City:");
        return input.nextLine();
    }

    /**
     * prompt_State  generates a standard output prompt for the State to be entered
     * @param in
     * @return
     */
    public static String prompt_State(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("State:");
        return input.nextLine();
    }

    /**
     * prompt_Zip  generates a standard output prompt for the Zip code to be entered
     * @param in
     * @return
     */
    public static int prompt_Zip(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("Zipcode:");
        return input.nextInt();
    }

    /**
     * prompt_Telephone  generates a standard output prompt for the Telephone number to be entered
     * @param in
     * @return
     */
    public static String prompt_Telephone(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("Telephone:");
        return input.nextLine();
    }

    /**
     * prompt_Email  generates a standard output prompt for the Email address.data.Address to be entered
     * @param in
     * @return
     */
    public static String prompt_Email(InputStream in) {
        Scanner input = new Scanner(in);
        System.out.println("Email:");
        return input.nextLine();
    }

    /**
     * prompt_Menu  generates a standard output prompt for the address.Menu options to be selescted
     * @return
     */
    public static char promptMenu()
    {
        System.out.println("********************************");
        System.out.println("Please enter your selection:\n");
        System.out.println("a)Loading from File");
        System.out.println("b)Addition");
        System.out.println("c)Removal");
        System.out.println("d)Find");
        System.out.println("e)Listing");
        System.out.println("f)Quit");
        System.out.println("*********************************");
        Scanner input = new Scanner(System.in);
        return input.nextLine().charAt(0);

    }
}
