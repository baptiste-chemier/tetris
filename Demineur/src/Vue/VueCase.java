/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.ModeleGrille;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author frederic
 */
public class VueCase extends JPanel implements Observer {    
    
    private int id;
    private ModeleGrille m;
    
    public VueCase(int i, ModeleGrille modele) {
        
        id = i;
        m = modele;
        
        this.setPreferredSize(new Dimension(30,30));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                caseClicked(event);
            }
        });
        
    }
    
    public void caseClicked(MouseEvent event){
        if (SwingUtilities.isRightMouseButton(event)) {
            m.updtateGrille(id);

        }
        if (SwingUtilities.isLeftMouseButton(event)) {
            m.calcGrille(id);

        }
    }

    @Override
    public void update(Observable o, Object o1) {
        this.setBackground(Color.yellow);
    }
    
}
