package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import matr.Asignatura;

/**
 *
 * @author Jedabero
 */
public class AsignPanel extends CustomPanel {

    private Asignatura asign;

    public AsignPanel(JPanel parent, Asignatura asign) {
        super(parent, 400, 150, new Dimension(150, 110), new Dimension(210, 170));
        this.asign = asign;

        if (!asign.puedeMatr() && !asign.isAprobada()) {
            setBackground(new Color(200, 200, 200));
        } else if (asign.isMatriculada()) {
            setBackground(new Color(250, 250, 250));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int txth = getFontHeight();
        int y = getHeight() / 2 - txth;
        String text;
        if (!isInside()) {
            text = asign.getNombre() + "\n" + asign.getCodigo();
        } else {
            text = asign.toString();
            y -= (txth * 4);
        }
        for (String line : text.split("\n")) {
            int lineLen = getStringLen(line);
            g.drawString(line, getWidth() / 2 - lineLen / 2, y += txth);
        }
    }

    public void setAsignatura(Asignatura asign) {
        this.asign = asign;
    }

    public Asignatura getAsignatura() {
        return asign;
    }

}
