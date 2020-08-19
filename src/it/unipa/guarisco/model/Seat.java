package it.unipa.guarisco.model;

/**
 * Bean che rappresenta una sdraio
 * @author anton
 */
public class Seat {
    private int id, idclient, posX, posY;
    private boolean available = false;
    private final String NA = "Not assigned";
    
    public Seat(int id, int idC, int posX, int posY, boolean av){
        this.id = id;
        this.idclient = idC;
        this.posX = posX;
        this.posY = posY;
        this.available = av;
    }
    
    public Seat(int id, int posX, int posY, boolean av){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.available = av;
    }

    public int getId() {
        return id;
    }

    public int getIdclient() {
        return idclient;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getNA() {
        return NA;
    }
}