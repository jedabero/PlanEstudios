package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
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

    public PeriodPanel(Periodo periodo) {
        super();
        this.periodo = periodo;
        Nodo<Asignatura> x = periodo.getCab();
        setSize(150 * periodo.getTotalAsignaturas() + 100, 120);
        while (null != x) {
            add(new AsignPanel(this, x.getItem()));
            x = x.getSig();
        }

        setPreferredSize(new Dimension(getWidth() + 60, getHeight() + 60));
        setBorder(BorderFactory.createLineBorder(Color.blue));
    }

}
