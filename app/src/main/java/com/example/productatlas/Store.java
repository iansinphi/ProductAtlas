package com.example.productatlas;

//This declares the table for the database
//the variables are the column headings to use.
public class Store {
    private int id;
    private String name;
    private int shelf;
    private String storeDesc;
    private double price;
    private int quantity;
    private String classification;

    //Constructor with no variables
    public Store()
    {
    }
    //Constructor with full variables
    public Store(int id,String name,int shelf, String storeDesc,
                 double price, int quantity, String classification){
        this.id = id;
        this.name = name;
        this.shelf = shelf;
        this.storeDesc = storeDesc;
        this.price = price;
        this.quantity = quantity;
        this.classification = classification;
    }
    //BELOW IS JUST SETTERS AND GETTERS FOR THE CLASS---
    //Setter for id
    public void setId(int id) {
        this.id = id;
    }
    //Setter for name
    public void setName(String name) {
        this.name = name;
    }
    //Setter for shelf
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }
    //Setter for StoreDesc
    public void setStoreDesc(String storeDesc){
        this.storeDesc = storeDesc;
    }
    //Setter for price
    public void setPrice(double price){
        this.price = price;
    }
    //Setter for quantity
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    //Setter for classification
    public void setClassification(String x) {this.classification = x; }
    //Getter for id
    public int getId() {
        return id;
    }
    //Getter for name
    public String getName() {
        return name;
    }
    //Getter for shelf
    public int getShelf() {
        return shelf;
    }
    //Getter for StoreDesc
    public String getStoreDesc(){
        return storeDesc;
    }
    //Getter for price
    public double getPrice(){
        return price;
    }
    //Getter for quantity
    public int getQuantity() {
        return quantity;
    }
    //Getter for classification
    public String getClassification() { return classification; }
}
