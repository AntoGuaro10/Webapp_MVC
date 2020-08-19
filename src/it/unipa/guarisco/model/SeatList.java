package it.unipa.guarisco.model;

import java.util.ArrayList;
import java.util.List;

/**
 *	Lista di sdraio
 * @author anton
 */
public class SeatList {
    private List<Seat> list;
    
    public SeatList(){
        this.list = new ArrayList<>();
    }

    public List<Seat> getList() {
        return list;
    }
    
    public Seat getPos(int x, int y, List<Seat> list){
        for(Seat s: list){
            if(s.getPosX() == x && s.getPosY() == y){
                return s;
            }
        }
        return null;
    }
}