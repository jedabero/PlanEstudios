package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
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
        StringBuilder foot = new StringBuilder("Total creditos: ");
        foot.append(periodo.getTotalCreditosMatriculados());
        foot.append("    ").append("Total asignaturas: ");
        foot.append(periodo.getTotalAsignaturas());
        foot.append("    ").append("Promedio: ");
        foot.append(periodo.getPromedio());
        JLabel footer = new JLabel(foot.toString(), JLabel.CENTER);
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

        ArrayList<Asignatura> lista = periodo.getListaAsignaturas();
        for (Asignatura x : lista) {
            center.add(new AsignPanel(this, x), BorderLayout.CENTER);
        }

        add(center, BorderLayout.CENTER);
        setPreferredSize(new Dimension(getWidth() + 30, getHeight() + 30));
        setBorder(BorderFactory.createLineBorder(Color.blue));
    }

    public Periodo getPeriodo() {
        return periodo;
    }

}
