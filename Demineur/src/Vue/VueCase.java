/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.*;
import Modele.ModeleGrille;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author frederic
 */
public class VueCase extends JPanel {
    ModeleGrille m;
    
    public VueCase() {
        super();
        m = new ModeleGrille();
        m.afficheTestGrille();
        setBackground(Color.white);
        
        addMouseListener(new MouseAdapter() {
        
            @Override
            public void mouseClicked(MouseEvent event) {
                if (SwingUtilities.isRightMouseButton(event))
                {
                    super.mouseClicked(event);
                    setBackground(Color.red);
                    m.updtateGrille((event.getComponent().getX()-7)/28, (event.getComponent().getY()-7)/25);
                }
            }
        });
        
    }
    
}
