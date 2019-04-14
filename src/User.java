package sample;

//Ronan O'Brien - 13050044
import java.util.ArrayList;

public abstract class User {

    protected String username;
    protected String password;

    protected User() {
    }

    /**
     * New user consturctor - used for both Admins and Customers
     * @param username
     * @param password
     */
    protected User(String username, String password) {
        this.password = password;
        this.username = username;
    }


    /**
     * Checks whether a login is correct
     * @return boolean
     *
     */
    public abstract boolean checkLogin();

    /**
     *
     * @return List of Admins or Customers
     */
    public abstract ArrayList<String> readInUserList();

    /**
     *
     * @return index of a particular user - useful to find credit, login and password data.
     */
    public abstract int indexofUser();
}


