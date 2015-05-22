/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author frederic
 */
public class VueVue extends JFrame  implements Observer {

    private ModeleGrille modeleGrille;
    private JComponent pan, infos, principal;
    private Border blackline;
    private ArrayList<VueCase> tabCases;
    private JLabel nombreDeMines;
    
    public VueVue() {
        super();
        
        tabCases =  new ArrayList<>();
        modeleGrille = new ModeleGrille();
        initComponent();
        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

        modeleGrille.addObserver(this);

    }
    
    public void initComponent()
    {
        principal = new JPanel();
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");        
        infos = new JPanel();
        m.add(mi);   
        jm.add(m);
        setJMenuBar(jm);        
        setTitle("Ma première fenêtre");
        pan = new JPanel (new GridLayout(modeleGrille.getNbLigne(), modeleGrille.getNbColonne()));
        blackline = BorderFactory.createLineBorder(Color.black,1);
        
        nombreDeMines = new JLabel(modeleGrille.getNbMine()+"");
        
        setSize(new Dimension(680, 520));
        pan.setPreferredSize(new Dimension(450, 450));
        infos.setPreferredSize(new Dimension(200, 450));
        infos.add(nombreDeMines,BorderLayout.NORTH);
        principal.add(pan, BorderLayout.WEST);
        principal.add(infos, BorderLayout.EAST);
        add(principal, BorderLayout.EAST);
    }
    
    public void build() {
        for(int i = 0;i< modeleGrille.getNbLigne()*modeleGrille.getNbColonne();i++){
            VueCase maCase = new VueCase(i,modeleGrille);            
            if (modeleGrille.getCase(i).hasMine() == 1) {
                maCase.setBackground(Color.red);
            } else {
                maCase.setBackground(Color.white);
            }
            
            maCase.setBorder(blackline);
            pan.add(maCase);
            tabCases.add(i, maCase);
            pan.setBorder(blackline);
            infos.setBorder(blackline);
        }
    }

    @Override
    public void update(Observable o, Object o1) {


        for(int i = 0; i<modeleGrille.getNbColonne() * modeleGrille.getNbLigne(); i++){
            if(modeleGrille.getCase(i).isDrapeau() == 1){
                tabCases.get(i).setBackground(Color.green);
            }
            
        }
        
        nombreDeMines.setText(modeleGrille.getNbMine()+"");
        pan.setBorder(blackline);
        add(pan);
        
    }
}
