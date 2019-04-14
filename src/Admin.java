package sample;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends sample.User {

    ArrayList<String> admins = readInUserList();

    /**
     * Constructor for Admin Class
     * @param userName
     * @param password
     */
    public Admin(String userName, String password){
        this.username = userName;
        this.password = password;
    }


    /**
     *
     * Abstract Method in User.class
     * @return brings in a list of Admins from the App
     */

    @Override
    public ArrayList<String> readInUserList(){
        ArrayList<String> admins = new ArrayList<String>();
        try {

            BufferedReader br = new BufferedReader(new FileReader("Admins.txt"));
            String line = null;

            while ((line = br.readLine()) != null) {
                admins.add(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return admins;
    }


    /**
     * Abstract Method in User.class
     * @return the index location of the user in an arraylist
     */

    @Override
    public int indexofUser(){
        readInUserList();
        int index=-1;
        for(int i = 0; i<admins.size(); i++){
            String[] temp = (admins.get(i).split(","));
            if (temp[0].equalsIgnoreCase(username)){
                index = i;
            }
        }
        return index;
    }


    /**
     *
     * Abstract Method in User.class
     * @return A boolean true if the User exists in the admin list provided.
     */

    @Override
    public boolean checkLogin(){
        boolean validLogin;
        //ArrayList<String> customers = readInCustomerList();
        readInUserList();
        String temp[] = admins.get(indexofUser()).split(",");
        if(temp[1].equalsIgnoreCase(password)){
            validLogin = true;
        }
        else{
            validLogin = false;
        }
        return validLogin;


    }

}
