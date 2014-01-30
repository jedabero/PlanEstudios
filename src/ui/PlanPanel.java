package ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.green));

        if (!plan.vacio()) {
            int axis = 0;
            switch (o) {
                case HORIZONTAL:
                    axis = BoxLayout.X_AXIS;
                    break;
                case VERTICAL:
                    axis = BoxLayout.Y_AXIS;
                    break;
            }
            setLayout(new BoxLayout(this, axis));
            Periodo x = plan.getPri();
            while (null != x) {
                PeriodPanel pp = new PeriodPanel(x, o);
                add(pp);
                x = x.getSig();
            }
        } else {

        }
    }

    public void anadirPeriodo(Periodo p) {
        /*jhf
         jfmfj*/
    }

}
