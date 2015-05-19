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
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
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

    ModeleGrille modeleGrille;
    JComponent pan, infos, principal;
    Border blackline;
    private JPanel[][] tabCases;
    
    public VueVue() {
        super();
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
        modeleGrille = new ModeleGrille();
        tabCases = new JPanel[15][15];
        infos = new JPanel();
        m.add(mi);   
        jm.add(m);
        setJMenuBar(jm);        
        setTitle("Ma première fenêtre");
        pan = new JPanel (new GridLayout(modeleGrille.getNbLigne(), modeleGrille.getNbColonne()));
        blackline = BorderFactory.createLineBorder(Color.black,1);
        
        setSize(new Dimension(680, 520));
        pan.setPreferredSize(new Dimension(450, 450));
        infos.setPreferredSize(new Dimension(200, 450));
        principal.add(pan, BorderLayout.WEST);
        principal.add(infos, BorderLayout.EAST);
        add(principal, BorderLayout.WEST);
    }
    
    public void build() {

        for(int i = 0; i < modeleGrille.getNbLigne() ;i++){
            for(int j = 0; j < modeleGrille.getNbColonne(); j++)
            {
               JPanel vc = new JPanel();
               if(modeleGrille.getTabCases()[i][j].hasMine() == 1){
                   vc.setBackground(Color.red);
               }else{
                   vc.setBackground(Color.white);
               }
               

               vc.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        if (SwingUtilities.isRightMouseButton(event))
                        {
                            super.mouseClicked(event);
                            modeleGrille.updtateGrille((event.getComponent().getY()-7)/25,(event.getComponent().getX()-7)/28);
                            
                        }
                    }
                });
                vc.setBorder(blackline);
                vc.setSize(30, 30);
                pan.add(vc);  
                tabCases[i][j] = vc;
            }
        }
        pan.setBorder(blackline);
        infos.setBorder(blackline);
    }

    @Override
    public void update(Observable o, Object o1) {
        
        for(int i = 0; i < modeleGrille.getNbLigne() ;i++){
            for(int j = 0; j < modeleGrille.getNbColonne(); j++)
            {
                if(modeleGrille.getTabCases()[i][j].isDrapeau() == 1){
                    tabCases[i][j].setBackground(Color.green);
                }
            }
        }  
        pan.setBorder(blackline);
        add(pan);
    }
}
