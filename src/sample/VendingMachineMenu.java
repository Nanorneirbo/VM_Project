package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.IOException;

/**
 A menu from the vending machine.
 */
public class VendingMachineMenu extends sample.Login
{
    private Scanner in;
    sample.Customer customer = new sample.Customer(Username);

    /**
     Constructs a VendingMachineMenu object
     */
    public VendingMachineMenu()
    {
        in = new Scanner(System.in);

    }

    /**
     Runs the vending machine system.
     @param machine the vending machine
     */
    public void run(sample.VendingMachine machine)
            throws IOException
    {
        boolean more = true;

        while (more)
        {
            System.out.println("S)how products  C)heck Balance  B)uy  A)dd product   Q)uit");
            String command = in.nextLine().toUpperCase();

            if (command.equals("S"))
            {
                for (sample.Product p : machine.getProductTypes())
                    System.out.println(p);
            }
            else if (command.equals("C"))
            {
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println("€" + df.format(customer.getCredit()));

            }

            else if (command.equals("B"))
            {
                try
                {

                        sample.Product p = (sample.Product) getChoice(machine.getProductTypes());
                        machine.buyProduct(p);
                        System.out.println("Purchased: " + p);
                        customer.setCredit(customer.getCredit() - p.getPrice());
                    }

                catch (sample.VendingException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            else if (command.equals("A"))
            {
                System.out.println("Description:");
                String description = in.nextLine();
                System.out.println("Location");
                String location = in.nextLine();
                System.out.println("Price:");
                double price = in.nextDouble();
                System.out.println("Quantity:");
                int quantity = in.nextInt();
                in.nextLine(); // read the new-line character
                machine.addProduct(new sample.Product(description,location, price, quantity));
            }
            else if (command.equals("Q"))
            {
                customer.updateUserList();
                more = false;
            }
        }
    }

    private Object getChoice(Object[] choices)
    {
        while (true)
        {
            char c = 'A';
            for (Object choice : choices)
            {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }
}
