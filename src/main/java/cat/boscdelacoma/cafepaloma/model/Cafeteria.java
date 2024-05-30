/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.cafepaloma.model;

/**
 *
 * @author noureddin
 */
public class Cafeteria extends Product {
    private String size;

    public Cafeteria(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }
    public Cafeteria(int id , String name, double price, String size) {
        super(id , name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    
}