package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import matr.Asignatura;
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
        setToolTipText(periodo.getNombre());

        JPanel center = new JPanel();
        JScrollPane scrollView = new JScrollPane(center);

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
                add(description, BorderLayout.NORTH);
                int h = 110 * (periodo.getTotalAsignaturas() / 2 + 1);
                center.setPreferredSize(new Dimension(320, h));
                setSize(320, 550);
                break;
            case VERTICAL:
                add(description, BorderLayout.WEST);
                int numero = (periodo.getTotalAsignaturas() / 2) + 1;
                center.setPreferredSize(new Dimension(150 * numero, 120));
                setSize(900, (periodo.getTotalAsignaturas() > 6) ? 240 : 120);
                break;
        }

        for (Asignatura x : periodo.getListaAsignaturas()) {
            center.add(new AsignPanel(this, x));
        }

        add(scrollView, BorderLayout.CENTER);
        setPreferredSize(new Dimension(getWidth() + 30, getHeight() + 30));
        
        if (GUI.debugMode) {
            setBorder(BorderFactory.createLineBorder(Color.blue));
        }
        
    }

    public Periodo getPeriodo() {
        return periodo;
    }

}
