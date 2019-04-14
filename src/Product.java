
package sample;

//Ronan O'Brien - 13050044

public class Product {
    private String name = "";
    private String location = "";
    private double price = 0.0;
    private int quantity = 0;

    /**
     *
     * @param name
     * @param location
     * @param price
     * @param quantity
     * product constructor
     */

    public Product(String name, String location, double price, int quantity) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     *
     * @return Name of Product
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Location of Product
     */

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return price of product
     */

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return Quantity of products in the mahcine
     */
    public int getQuantiy() {
        return quantity;
    }

    public void setQuantiy(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return to String override
     */
    @Override
    public String toString(){
        return name + "," + location + "," + price + "," + quantity;
    }
}




