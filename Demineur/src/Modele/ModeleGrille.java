/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Vue.VueCase;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Epulapp
 */
public class ModeleGrille extends Observable{
    
    //private ModeleCase[][] tabCases;
    private ArrayList<ModeleCase> tabCases;
    private int nbColonne = 15;
    private int nbLigne = 15;
    private int nbMine = (int) ceil((nbColonne*nbLigne)/3);

    public void setNbMine(int nbMine) {
        this.nbMine = nbMine;
    }

    public int getNbMine() {
        return nbMine;
    }
    
    public int getNbColonne() {
        return nbColonne;
    }

    public void setNbColonne(int nbColonne) {
        this.nbColonne = nbColonne;
    }

    public int getNbLigne() {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne) {
        this.nbLigne = nbLigne;
    }
    
    public ModeleCase getCase(int i){
        return tabCases.get(i);
    }
    
    public ModeleGrille() {
        tabCases = new ArrayList<>();
        for(int i = 0; i< nbLigne * nbColonne;i++){
            ModeleCase m = new ModeleCase(i);
            tabCases.add(i, m);
        }
        remplirMine();
        
    }
    
    public void updtateGrille(int id)
    {
        getCase(id).setDrapeau(1);
        if(getCase(id).hasMine() == 1){
            nbMine--;
            if(nbMine == 0){
                finPartie();
            }
        }
        setChanged();
        notifyObservers();
        System.out.println(this.countObservers());
    }
    
    public void afficheTestGrille(){
        for(int i = 0; i<nbColonne*nbLigne;i++){
            System.out.print(getCase(i).hasMine());
        }
    }
    
    public void remplirMine()
    {    
        int nbMineRemplissage = nbMine;
        while(nbMineRemplissage > 0)
        {
            int id = genererAleatoire();
            
            if(getCase(id).hasMine() == 0)
            {
                getCase(id).setMine(1);
                nbMineRemplissage--;
            }
        }
    }
    
    public int genererAleatoire()
    {
        Random r = new Random();
        int id = r.nextInt((nbColonne * nbLigne));
        return id;
    }

    private void finPartie() {
        System.out.println("GAGNE");
    }
    
    public void calcGrille(int x, int y){
        int nbMinesVoisine;

    }
    
    public int getNbVoisin(int x,int y){
        int nbMinesVoisine = 0;
        

        return nbMinesVoisine;
    }
    
}
