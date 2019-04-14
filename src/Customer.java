package sample;


//Ronan O'Brien - 13050044

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends sample.User {

    private double credit = 0.0;
    ArrayList<String> customers = readInUserList();

    // public ArrayList<String> customers = readInCustomerList();

    public Customer(){
    }

    // Overloaded consructer used to find credit
    public Customer(String userName){
        this.username = userName;
        String temp[] = customers.get(indexofUser()).split(",");
        password = temp[1];
        credit = Double.parseDouble(temp[2]);
    }
//Overloaded Constructor used to check login credentials
    public Customer(String userName, String password){
        this.username = userName;
        this.password = password;
    }

    /**
     *
     * @return Arraylist of the users in String format
     */


    public ArrayList<String> readInUserList(){
        ArrayList<String> customers = new ArrayList<String>();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
            String line = null;

            while ((line = br.readLine()) != null) {
                customers.add(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return customers;
    }


    /**
     * Overriden abstract method - used to identidfy what element of an arraylist te user is in
     * @return index (Integer)
     */

    public int indexofUser(){
        //ArrayList<String> customers = readInCustomerList();
        readInUserList();
        int index=-1;
        for(int i = 0; i<customers.size(); i++){
            String[] temp = (customers.get(i).split(","));
            if (temp[0].equalsIgnoreCase(username)){
                index = i;
            }
        }
        return index;
    }


    /**
     * Overridden abstract method form user
     * @return boolean check of login credentials
     */
    @Override
    public boolean checkLogin(){
        boolean validLogin;
        //ArrayList<String> customers = readInCustomerList();
        readInUserList();
        String temp[] = customers.get(indexofUser()).split(",");
        if(temp[1].equalsIgnoreCase(password)){
            validLogin = true;
        }
        else{
            validLogin = false;
        }
        return validLogin;


    }

    /**
     *
     * @return credit
     */

    public double getCredit(){

        //String temp[] = customers.get(indexofUser()).split(",");
        //credit = Double.parseDouble(temp[2]);
        return credit;
    }

    /**
     *
     * @param price
     * @return modified credit
     */
    public double buyProduct(double price){
        credit = getCredit() - price;
        return credit;
    }

// Credit Setter
    public void setCredit(double credit) {
        this.credit = credit;
    }


    public void updateUserList(){
        DecimalFormat df = new DecimalFormat("#.00");

        customers.set(indexofUser(), username + "," + password + "," + df.format(getCredit()));

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("Users.txt"));
            for (int i = 0; i < customers.size(); i++)
                pw.println(customers.get(i));
            pw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }


}
