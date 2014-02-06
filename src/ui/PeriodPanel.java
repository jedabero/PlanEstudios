package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import matr.Asignatura;
import matr.Nodo;
import matr.Periodo;

/**
 *
 * @author Jedabero
 */
public class PeriodPanel extends JPanel {

    private Periodo periodo;

    public PeriodPanel(Periodo periodo, PlanPanel.Orientacion o) {
        super();
        this.periodo = periodo;
        this.setToolTipText(periodo.getNombre());
        switch (o) {
            case HORIZONTAL:
                int h = 110 * (periodo.getTotalAsignaturas() / 2 + 1);
                setSize(150 + 150, h);
                break;
            case VERTICAL:
                setSize(150 * periodo.getTotalAsignaturas() + 150, 120);
                break;
        }
        Nodo<Asignatura> x = periodo.getCab();
        while (null != x) {
            add(new AsignPanel(this, x.getItem()));
            x = x.getSig();
        }

        setPreferredSize(new Dimension(getWidth() + 30, getHeight() + 30));
        setBorder(BorderFactory.createLineBorder(Color.blue));
    }

}
