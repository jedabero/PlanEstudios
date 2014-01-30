package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import matr.Periodo;
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
        this.setBorder(BorderFactory.createLineBorder(Color.green));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        Periodo x = plan.getPri();
        while (null != x) {            
            add(new PeriodPanel(x), gbc);
            switch(o){
                case HORIZONTAL:
                    gbc.gridx++;
                    break;
                case VERTICAL:
                    gbc.gridy++;
                    break;
            }
            x = x.getSig();
        }
        
    }

}
