/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Epulapp
 */
public class ModeleCase {
    
    private int drapeau;
    private int mine;
    private int isReturn;
    private int id;

    public int getId() {
        return id;
    }
    
    
    public int isReturn(){
        return isReturn;
    }
    
    public void setReturn(int isReturn){
        this.isReturn = isReturn;
    }
    
    public int hasMine() {
        return mine;
    }
    
    public void setMine(int mine) {
        this.mine = mine;
    }

    public int isDrapeau() {
        return drapeau;
    }

    public void setDrapeau(int drapeau) {
        this.drapeau = drapeau;
    }    
    
    public ModeleCase(int id)
    {
        this.id = id;
    }
    public ModeleCase(){
        setDrapeau(0);
        setMine(0);
        setReturn(0);
    }
}
