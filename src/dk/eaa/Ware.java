package dk.eaa;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 10/5/12
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Ware {

    private String name, unit;
    private double amount, price;
    private int id;

    public Ware(String name){

        this.name = name;
    }

    public Ware(String name, String unit, double amount, double price){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "Name: " + getName() + " Pris: " + getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
