/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.cafepaloma.model;

/**
 *
 * @author noureddin
 */
public class Entrepans extends Product {

    private String filling;

    public Entrepans(int id, String name, double price, String filling) {
        super(id, name, price);
        this.filling = filling;
    }

    public Entrepans(String text, double parseDouble, String text0) {
        super(text, parseDouble);
        this.filling = text0;
    }

    public String getFilling() {
        return filling;
    }
     public void setFilling(String filling) {
        this.filling = filling;
    }


}
