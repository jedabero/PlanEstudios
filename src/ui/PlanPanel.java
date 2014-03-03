package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
        super(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.green));

        JPanel center = new JPanel();
        StringBuilder desc = new StringBuilder(plan.getEstudiante().toString());
        JLabel description = new JLabel(desc.toString(), JLabel.CENTER);
        add(description, BorderLayout.NORTH);

        StringBuilder foot = new StringBuilder("Total creditos: ");
        foot.append(plan.getTotalCreditos());
        foot.append("    ").append("Total periodos: ");
        foot.append(plan.getTotalPeriodos());
        JLabel footer = new JLabel(foot.toString(), JLabel.CENTER);
        add(footer, BorderLayout.SOUTH);

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
            center.setLayout(new BoxLayout(center, axis));
            for (Periodo p : plan.getListaPeriodos()) {
                center.add(new PeriodPanel(p, o));
            }
        } else {
        }
        add(center);
    }

    public void agregarPeriodo(Periodo p) {

    }

}
