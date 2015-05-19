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
        afficheTestGrille();
    }
    
    public void updtateGrille(int x, int y)
    {
        tabCases[x][y].setDrapeau(1);
        
        if(tabCases[x][y].hasMine() == 1){
            nbMine--;
            if(nbMine == 0){
                finPartie();
            }
        }

        setChanged();
        notifyObservers();
    }
    
    public void afficheTestGrille(){
        for(int i=0;i<nbColonne;i++){
            for(int j=0;j<nbLigne;j++){
                System.out.print(tabCases[i][j].hasMine());
            }
            System.out.println("\n");
        }
    }
    
    public void remplirMine()
    {    
        int nbMineRemplissage = nbMine;
        while(nbMineRemplissage > 0)
        {
            int[] coord = genererAleatoire();
            
            if(tabCases[coord[0]][coord[1]].hasMine() == 0)
            {
                tabCases[coord[0]][coord[1]].setMine(1);
                nbMineRemplissage--;
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

    private void finPartie() {
        System.out.println("GAGNE");
    }
    
    public void calcGrille(int x, int y){
        int nbMinesVoisine;
        if(tabCases[x][y].isReturn()== 0){
            tabCases[x][y].setReturn(1);
            nbMinesVoisine = getNbVoisin(x, y);
            System.out.println(nbMinesVoisine);
        }
    }
    public int getNbVoisin(int x,int y){
        int nbMinesVoisine = 0;
        
        for(int i=x-1;i<=x+1;i++){
            //for(int j=y-1;j<=y+1;j++){
            System.out.println("x: "+i+", Y: "+y);
            System.out.println("Mine ?:"+tabCases[i][y].hasMine());
                if(i>=0 && i<this.nbMine && y>=0 && y<this.nbColonne){
                    if(tabCases[i][y].hasMine() == 1) nbMinesVoisine++;
                }
            //}
        }

        return nbMinesVoisine;
    }
    
}
