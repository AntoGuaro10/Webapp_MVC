package it.unipa.guarisco.model;

/**
 * Bean che rappresenta un ordine
 * @author anton
 */
public class Order {
    private int id, idclient, quantity;
    private float price;
    private String description, status;
    
    public Order(int idc, String desc, int q, float p, String s){
        this.idclient = idc;
        this.description = desc;
        this.quantity = q;
        this.price = p;
        this.status = s;
    }

    public Order(int id, int idc, String desc, int q, float p, String s){
        this.id = id;
        this.idclient = idc;
        this.description = desc;
        this.quantity = q;
        this.price = p;
        this.status = s;
    }

    public int getIdclient() {
        return idclient;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}
