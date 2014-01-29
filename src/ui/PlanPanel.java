package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import matr.Plan;

/**
 *
 * @author Jedabero
 */
public class PlanPanel extends JPanel {

    public enum Orientacion {

        VERTICAL,
        HORIZONTAL;
    }
    
    public PlanPanel(Plan plan, Orientacion o) {
        super(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        
    }

}
