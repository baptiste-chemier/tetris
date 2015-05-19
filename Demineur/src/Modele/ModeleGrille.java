/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class ModeleGrille extends Observable{
    
    private ModeleCase[][] tabCases;
    private int nbColonne = 15;
    private int nbLigne = 15;

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
            }
        }
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
    
}
