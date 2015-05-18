/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.border.Border;

/**
 *
 * @author frederic
 */
public class VueVue extends JFrame {

    ModeleGrille modeleGrille;
    
    public VueVue() {
        super();
        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        
        modeleGrille.addObserver(new Observer(){
            @Override
            public void update(Observable o, Object arg) {
                build();
            }
        });
    }
    
    public void build() {
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");
        modeleGrille = new ModeleGrille();
        
        m.add(mi);   
        jm.add(m);
        setJMenuBar(jm);
        
        setTitle("Ma première fenêtre");
        setSize(450, 450);
        JComponent pan = new JPanel (new GridLayout(modeleGrille.getNbLigne(), modeleGrille.getNbColonne()));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);

        for(int i = 0; i < modeleGrille.getNbLigne() ;i++){
            for(int j = 0; j < modeleGrille.getNbColonne(); j++)
            {
                JComponent ptest = new VueCase();
                ptest.setBorder(blackline);
                ptest.setSize(30, 30);
                pan.add(ptest);  
            }
        }
        
        
        pan.setBorder(blackline);
        add(pan);

    }
}
