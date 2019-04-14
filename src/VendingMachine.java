package sample;

//Ronan O'Brien - 13050044
import javafx.scene.control.Button;

import java.io.*;
import java.util.ArrayList;

/**
 A vending machine.
 */
public class VendingMachine extends sample.UserScreen
{
    ArrayList<Button> buttonProducts;
    ArrayList<sample.Product> allProducts;
    sample.Customer customer = new sample.Customer(Username);

    /**
     Constructs a VendingMachine object.
     */
    //modified to accept balance rather than coins
    // modified to accept stock totals from the csv
    public VendingMachine()
    {

    buttonProducts = getbuttons();
    allProducts = getProducts();








    }
///continue here make a new constructor to get username and password over////////////////////////////
   // public VendingMachine(String Username, String Password)

    /**
     *
     * @return an arraylist of buttons - allows for different size product lists
     */

    public ArrayList<Button> getbuttons(){
        String name = "";
        String location = "";
        double price = 0.0;
        int quantity = 0;

        ArrayList<String> products = new ArrayList<>();
        ArrayList<sample.Product> allproducts = new ArrayList<>();
        ArrayList<Button> allButtons = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Product.dat"));
            String line = null;

            while ((line = br.readLine()) != null) {
                products.add(line);
            }
            int i;

            //break up the strings
            for(i=0; i<products.size();i++) {
                String[] temp = (products.get(i).split(","));
                name = temp[0];
                location = temp[1];
                price = Double.parseDouble(temp[2]);
                quantity = Integer.parseInt(temp[3]);
                allproducts.add(new sample.Product(name,location, price, quantity));
            }


        } catch (
                IOException e) {

            e.printStackTrace();
        }
        int i;
        for(i=0; i<allproducts.size();i++){
            allButtons.add(new Button(allproducts.get(i).getName()));
            allButtons.get(i).setPrefSize(100,100);
        }

        return allButtons;
    }
    //method to change the Arraylist of Products




    /**
     *
     * @return the data from products.dat as an ArrayList of Products
     */
    public ArrayList<sample.Product> getProducts(){
        String name = "";
        String location = "";
        double price = 0.0;
        int quantity = 0;

        ArrayList<String> products = new ArrayList<>();
        ArrayList<sample.Product> allproducts = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Product.dat"));
            String line = null;

            while ((line = br.readLine()) != null) {
                products.add(line);
            }
            int i;

            //break up the strings
            for(i=0; i<products.size();i++) {
                String[] temp = (products.get(i).split(","));
                name = temp[0];
                location = temp[1];
                price = Double.parseDouble(temp[2]);
                quantity = Integer.parseInt(temp[3]);
                allproducts.add(new sample.Product(name,location, price, quantity));
            }


        } catch (
                IOException e) {

            e.printStackTrace();
        }
        return allproducts;
    }

    //vending machine print out new products set products


    /**
     Gets the type of products in the vending machine.
     @return an array of products sold in this machine.
     */
    public sample.Product[] getProductTypes()
    {
        ArrayList<sample.Product> types = new ArrayList<>();

        for (sample.Product p : allProducts)
            if (!types.contains(p)) types.add(p);

        sample.Product[] r = new sample.Product[types.size()];
        for (int i = 0; i < types.size(); i++)
            r[i] = types.get(i);
        return r;
    }



    /**
     Buys a product from the vending machine.
     @param p the product we want to buy
     */
    public void buyProduct(sample.Product p)
    {

        for (int i = 0; i < allProducts.size(); i++)
        {
            sample.Product prod = allProducts.get(i);

            //product comparison needs comparable to be implemented using to String for now.
            if (prod.getName().equals(p.getName()))
            {
                if (p.getPrice() <= customer.getCredit())
                {
                    prod.setQuantiy(prod.getQuantiy()-1);
                    allProducts.set(i,prod);
                    writeTofile();
                    return;

                }
                else
                {
                    throw new sample.VendingException("Insufficient money");
                }
            }
        }
        throw new sample.VendingException("No such product");
    }

    /**
     Adds a product to the vending machine.
     @param p the product to add
     */
    //modified to reflect the way quantity works now
    public void addProduct(sample.Product p)
    {
            getProducts();
            allProducts.add(p);
            writeTofile();
    }

    /**
     * writes an updated Products data file
     */
    public void writeTofile(){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("Product.dat"));
            for (int i = 0; i < allProducts.size(); i++)
                pw.println(allProducts.get(i).toString());
            pw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param addQ
     * @param j
     *
     * restock method
     */
    public void restockProduct(int addQ, int j){
        getProducts();
        int newQuantity = allProducts.get(j).getQuantiy() + addQ;
        allProducts.get(j).setQuantiy(newQuantity);
        writeTofile();
    }


}


