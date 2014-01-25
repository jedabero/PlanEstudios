package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import matr.Lista;
import matr.Periodo;

/**
 *
 * @author Jedabero
 */
public class PeriodPanel extends JPanel {

    private Periodo periodo;

    public PeriodPanel(Periodo periodo) {
        super();
        this.periodo = periodo;
        Lista.Nodo x = periodo.getLista().getCab();
        setSize(150 * periodo.getTotalAsignaturas() + 100, 120);
        while (null != x) {
            add(new AsignPanel(this, x.asign));
            x = x.sig;
        }

        setPreferredSize(new Dimension(getWidth() + 60, getHeight() + 60));
        setBorder(BorderFactory.createLineBorder(Color.blue));
    }

}
