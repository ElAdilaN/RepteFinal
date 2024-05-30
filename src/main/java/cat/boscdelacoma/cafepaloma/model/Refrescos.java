/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.cafepaloma.model;

/**
 *
 * @author noureddin
 */
public class Refrescos extends Product {

    private String flavor;

    public Refrescos(int id, String name, double price, String flavor) {
        super(id, name, price);
        this.flavor = flavor;
    }

    public Refrescos(String text, double parseDouble, String text0) {
        super(text, parseDouble);
        this.flavor = text0;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

}
