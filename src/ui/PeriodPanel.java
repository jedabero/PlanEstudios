package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
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
        super(new BorderLayout());
        this.periodo = periodo;
        this.setToolTipText(periodo.getNombre());
        JPanel center = new JPanel();
        JLabel description = new JLabel(periodo.getNombre(), JLabel.CENTER);
        String foot = "Total creditos: " +periodo.getTotalCreditosMatriculados();
        JLabel footer = new JLabel(foot, JLabel.CENTER);
        add(footer, BorderLayout.SOUTH);
        
        switch (o) {
            case HORIZONTAL:
                int h = 110 * (periodo.getTotalAsignaturas() / 2 + 1);
                setSize(150 + 150, h);
                add(description, BorderLayout.NORTH);
                break;
            case VERTICAL:
                setSize(150 * periodo.getTotalAsignaturas() + 150, 120);
                add(description, BorderLayout.WEST);
                break;
        }
        
        Nodo<Asignatura> x = periodo.getCab();
        while (null != x) {
            center.add(new AsignPanel(this, x.getItem()), BorderLayout.CENTER);
            x = x.getSig();
        }

        add(center, BorderLayout.CENTER);
        setPreferredSize(new Dimension(getWidth() + 30, getHeight() + 30));
        setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    public Periodo getPeriodo() {
        return periodo;
    }

}
