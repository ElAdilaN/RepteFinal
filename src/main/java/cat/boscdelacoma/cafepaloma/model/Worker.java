/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.cafepaloma.model;

/**
 *
 * @author noureddin
 */
public class Worker  {
    private int id ; 
    private String name  ;

    
    public Worker (int id , String name ){
    this.id = id ; 
    this.name = name ; 
}
    public Worker ( String name ){

    this.name = name ; 
}
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
