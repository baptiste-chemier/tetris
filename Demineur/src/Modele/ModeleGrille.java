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
    
    public void calcGrille(int id){
        
        propagVoisin(tabCases.get(id));

    }
    
    public void propagVoisin(ModeleCase caseCourante){
        if(caseCourante.isReturn() == 1 && caseCourante.hasMine() == 1)return;
        ArrayList<ModeleCase> nbVoisinsPiege = getNbVoisinPieges(caseCourante.getId());
        caseCourante.setReturn(1);
        if(getNbVoisinPieges(caseCourante.getId()).isEmpty())
            for(int i = 0; i< nbVoisinsPiege.size();i++){
                if(nbVoisinsPiege.get(i).isReturn() == 0){
                    
                    propagVoisin(nbVoisinsPiege.get(i));
                }
            }
        
    }
    
    public ArrayList<ModeleCase> getNbVoisinPieges(int id){
        ArrayList<ModeleCase> nbVoisins;
        ArrayList<ModeleCase> voisinPieges = new ArrayList<>();
        nbVoisins = getNbVoisin(id);

        for (int i = 0; i < nbVoisins.size(); i++) {
            if (nbVoisins.get(i).hasMine() == 1) {
               voisinPieges.add(nbVoisins.get(i));
            }
        }
        return voisinPieges;
    }
    
    public ArrayList<ModeleCase> getNbVoisin(int id){
        boolean gauche = false, droite = false, haut = false, bas = false;
        ArrayList<ModeleCase> nbCase = new ArrayList<>();
        
        if(id%(nbLigne) == 0)gauche=true;
        if(id%(nbLigne) == (nbLigne-1)) droite = true;
        if(id/(nbLigne) == 0) haut = true;
        if(id/(nbLigne) == (nbColonne-1)) bas = true;
        
        if(!gauche) 
            nbCase.add(getCase(id-1));
        if(!droite) 
            nbCase.add(getCase(id+1));
        if(!haut) 
            nbCase.add(getCase(id-nbLigne));
        if(!bas) 
            nbCase.add(getCase(id+nbLigne));
       
        return nbCase;
    }
    
}
