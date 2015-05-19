/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import static java.lang.Math.ceil;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Epulapp
 */
public class ModeleGrille extends Observable{
    
    private ModeleCase[][] tabCases;
    private int nbColonne = 15;
    private int nbLigne = 15;
    private int nbMine = (int) ceil((nbColonne*nbLigne)/3);
    
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

    public ModeleCase[][] getTabCases() {
        return tabCases;
    }

    public void setTabCases(ModeleCase[][] tabCases) {
        this.tabCases = tabCases;
    }

    public ModeleGrille() {
        tabCases = new ModeleCase[nbLigne][nbColonne];
        
        for(int i = 0; i <= nbLigne - 1; i++)
        {
            for(int j = 0; j <= nbColonne - 1; j++)
            {
                tabCases[i][j] = new ModeleCase();
                tabCases[i][j].setDrapeau(0);
                tabCases[i][j].setMine(0);
            }
        }
        remplirMine();
    }
    
    public void updtateGrille(int x, int y)
    {
        tabCases[x][y].setDrapeau(1);

        setChanged();
        notifyObservers();
    }
    
    public void afficheTestGrille(){
        for(int i=0;i<nbColonne;i++){
            for(int j=0;j<nbLigne;j++){
                System.out.print(tabCases[i][j].isDrapeau());
            }
            System.out.println("\n");
        }
    }
    
    public void remplirMine()
    {        
        while(nbMine > 0)
        {
            int[] coord = genererAleatoire();
            
            if(tabCases[coord[0]][coord[1]].hasMine() == 0)
            {
                tabCases[coord[0]][coord[1]].setMine(1);
                nbMine--;
            }
        }
    }
    
    public int[] genererAleatoire()
    {
        Random r = new Random();
        int x = r.nextInt(nbColonne );
        int y = r.nextInt(nbLigne );
        
        int[] tabAleatoire = new int[]{x, y};
        
        return tabAleatoire;
    }
}
