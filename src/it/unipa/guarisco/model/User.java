package it.unipa.guarisco.model;

/**
 * Bean che rappresenta un singolo utente
 * @author anton
 */
public class User {
    private int id;
    private String username, password, type;
    
    public User(int id, String username, String pass, String type){
        this.id = id;
        this.username = username;
        this.password = pass;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getType(){
        return type;
    }
}