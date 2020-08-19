package it.unipa.guarisco.Database;

import it.unipa.guarisco.model.Order;
import it.unipa.guarisco.model.Seat;
import it.unipa.guarisco.model.SeatList;
import it.unipa.guarisco.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Questa classe implementa tutti i metodi utili per le interazioni con il Database
 * @author anton
 */

public class DBUtilities {
	Context ctx = null;
	DataSource ds = null;
    
    /**
     * Metodo che crea un nuovo utente
     * @param user 
     */
    public void createUser(User user){
        try {
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "insert into user (id, username, password, type) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getType());
            
            statement.executeUpdate();
            statement.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @param employee
     * Metodo che crea un nuovo utente con type = "employee"  
     */
    public void createEmployee(User employee){
        try {
           
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "insert into user (id, username, password, type) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getUsername());
            statement.setString(3, employee.getPassword());
            statement.setString(4, employee.getType());
            
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException e) {
			e.printStackTrace();
		}
    }

    /**
     * Questo metodo fornisce le informazioni dell'utente selezionato tramite ID
     * @param id
     * @return 
     */
    public User getUser(int id){
        User user = null;
        try {
           
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select username, password, type from user where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()){
                user = new User(id, rs.getString("username"), rs.getString("password"), rs.getString("type"));
            }
            statement.close();
            rs.close();
            conn.close();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * Metodo che restituisce una lista di tutti gli utenti
     * @return 
     */
    public List<User> getAllUsers(){
        List<User> userList;
        User user;
        try{
            userList = new LinkedList<>();
            
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from user";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
                userList.add(user);
            }
            statement.close();
            rs.close();
            conn.close();
            return userList;
        } catch(SQLException ex){
        	return null;
        } catch (NamingException e) {
        	return null;
		}        
    }
    
    /**
     * Metodo che restituisce una lista di tutti gli utenti tranne l'admin
     * @return 
     */
    public List<User> getAll(){
        List<User> userList;
        User user;
        try{
            userList = new LinkedList<>();
           
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from user where not type=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "admin");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
                userList.add(user);
            }
            statement.close();
            rs.close();
            conn.close();
            return userList;
        } catch(SQLException ex){
            return null;
        } catch (NamingException e) {
			return null;
		}        
    }
    
    /**
     * Metodo che restituisce una lista contenente tutte le sdraio 
     * @return 
     */
    public SeatList getAllSeats(){
        SeatList seatList = new SeatList();
        Seat seat;
        try{
            List<Seat> list = seatList.getList();
           
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from seat";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                seat = new Seat(rs.getInt("id"), rs.getInt("idclient"), rs.getInt("posx"), rs.getInt("posy"), rs.getBoolean("available"));
                list.add(seat);
            }
            statement.close();
            rs.close();
            conn.close();
            return seatList;
        } catch(SQLException ex){
            return null;
        } catch (NamingException e) {
			return null;
		}        
    }
    
    /**
     * Metodo che restituisce tutti gli acquisti effettuati
     * @return 
     */
    public List<Order> getAllPurchases(){
        try{
            List<Order> list = new LinkedList<>();
            Order order;
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from orders";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                order = new Order(rs.getInt("id"), rs.getInt("idclient"), rs.getString("description"), rs.getInt("quantity"), rs.getFloat("price"), rs.getString("status"));
                list.add(order);
            }
            statement.close();
            rs.close();
            conn.close();
            return list;
        } catch(SQLException ex){
            return null;
        } catch (NamingException e) {
			return null;
		}        
    }
    
    /**
     * Metodo che restituisce gli ordini da completare
     * @return 
     */
    public List<Order> getIncompleteOrders(){
        try{
            List<Order> list = new LinkedList<>();
            Order order;
           
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from orders where status=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "Incomplete");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                order = new Order(rs.getInt("id"), rs.getInt("idclient"), rs.getString("description"), rs.getInt("quantity"), rs.getFloat("price"), rs.getString("status"));
                list.add(order);
            }
            statement.close();
            rs.close();
            conn.close();
            return list;
        } catch(SQLException ex){
            return null;
        } catch (NamingException e) {
			return null;
		}        
    }
    
    /**
     * Metodo che restituisce tutti gli ordini effettuati dall'utente specificato
     * @param idclient
     * @return 
     */
    public List<Order> getUserOrders(int idclient){
        try{
            List<Order> list = new LinkedList<>();
            Order order;
           
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "select * from orders where idclient=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idclient);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                order = new Order(rs.getInt("id"), idclient, rs.getString("description"), rs.getInt("quantity"), rs.getFloat("price"), rs.getString("status"));
                list.add(order);
            }
            statement.close();
            rs.close();
            conn.close();
            return list;
        } catch(SQLException ex){
            return null;
        } catch (NamingException e) {
        	return null;
		}        
    }

    /**
     * Metodo per aggiornare le credenziali dell'utente specificato
     * @param id
     * @param newpssw
     * @return 
     */
    public int updateUser(int id, String newpssw) {
        try {
          
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "update user set password=? where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, newpssw);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } catch (NamingException e) {
			return -1;
		}
    }

    /**
     * Metodo per aggiornare lo stato di un ordine
     * @param id
     * @return 
     */
    public int updateOrderStatus(int id){
         try {
           
        	 ctx = new InitialContext();
         	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
             Connection conn = ds.getConnection();
            String query = "update orders set status=? where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "Complete");
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } catch (NamingException e) {
        	e.printStackTrace();
        	return -1;
		}
    }

    /**
     * Metodo per aggiornare la disponibilita  di una sdraio
     * @param s
     * @return 
     */
    public int updateSeat(Seat s) {
        int id = s.getId();
        int idC = s.getIdclient();
        boolean av = s.isAvailable();
        try {
           
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "update seat set idclient=?, available=? where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idC);
            statement.setBoolean(2, av);
            statement.setInt(3, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException ex) {
            return -1;
        } catch (NamingException e) {
			return -1;
		}
    }

    /**
     * Metodo che serve a "liberare" la sdraio selezionata
     * @param s
     * @return 
     */
    public int free(Seat s) {
        boolean av = true;
        try {
            int id = s.getId();
           
            ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "update seat set idclient = null, available=? where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, av);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException | NullPointerException ex) {
            return -1;            
        } catch (NamingException e) {
			return -1;
		}
    }
    
    /**
     * Metodo che al logout dell'utente cancella tutti gli ordini associati a quella sessione
     * @return 
     */
    public int freeOrders(){
        try {           
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "TRUNCATE orders";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            conn.close();
            return 1;
        } catch (SQLException | NullPointerException | NamingException ex) {
            return -1;
        }
    }
    
    /**
     * Metodo che crea un nuovo ordine da completare
     * @param o
     * @return 
     */
    public int createOrder(Order o){
        try {
          
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "insert into orders (idclient, description, quantity, price, status) values (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, o.getIdclient());
            statement.setString(2, o.getDescription());
            statement.setInt(3, o.getQuantity());
            statement.setFloat(4, o.getPrice());
            statement.setString(5, o.getStatus());
            statement.executeUpdate();
            statement.close();
            conn.close();

            return 1;            
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Metodo che cancella l'utente selezionato
     * @param id
     */
    public void deleteUser(int id){
        try {
        	ctx = new InitialContext();
        	ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guarisco");
            Connection conn = ds.getConnection();
            String query = "delete from user where id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            
            
            statement.close();
            conn.close();
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
        }
    }
}