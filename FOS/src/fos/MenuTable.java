/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fos;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.scene.control.Button;

/**
 *
 * @author hp
 */
public class MenuTable {
    
    StringProperty ItemName = null;
    
    IntegerProperty Cost;
    
    IntegerProperty Quantity;
    
    Button Add;
    
    Button Remove;
    
    
    
    public MenuTable() {    
        this.ItemName = new SimpleStringProperty();
        this.Cost = new SimpleIntegerProperty();
        this.Quantity = new SimpleIntegerProperty();
        
        
    }
    

    
    public String getItemName() {
        return ItemName.get();
    }

    public void setItemName(String txt) {
        this.ItemName.set(txt);
    }    
    
    public StringProperty getMenuItemName() {
        return ItemName;
    }
    
    
    
    public int getCost() {
        return Cost.get();
    }
    
    public void setCost(int txt) {
        this.Cost.set(txt);
    }
    
    public IntegerProperty getMenuCost() {
        return Cost;
    }

     public int getQuantity() {
        return Quantity.get();
    }
    
    
    public void setQuantity(int txt) {
        this.Quantity.set(txt);
    }
    
    public IntegerProperty getMenuQuantity(){
        return Quantity;
    }
   
    public void setAdd(Button txt) {
        Add = txt;
    }
    
    public Button getAdd() {
        return Add;
    }
    
    public void setRemove(Button txt) {
        Remove = txt;
    }
    
    public Button getRemove() {
        return Remove;
    }
}


                 