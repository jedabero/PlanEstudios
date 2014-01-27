package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import matr.Asignatura;
import matr.Periodo;

/**
 *
 * @author jedabero
 */
public class GUI {

    private JFrame mainFrame;

    private JPanel panel;
    private JScrollPane scroll;

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.start();
    }

    private void start() {
        mainFrame = new JFrame("Matr");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1016, 638);
        panel = new JPanel(new GridBagLayout(), true);
        scroll = new JScrollPane(panel);
        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        Asignatura a = new Asignatura("Matematicas", "10001", 2, "I", null, null, 4);
        Asignatura b = new Asignatura("Matematicas 2", "10002", 2, "II", a, null, 2.5);
        Asignatura c = new Asignatura(true, "III", "Matematicas 3", "100T3", 2, b, null);
        Asignatura cc = new Asignatura(true, "III", "Matematicas Lab", "100L3", 1, null, c);

        Periodo lista = new Periodo();

        lista.agregarCabecera(a);
        lista.agregarCabecera(b);
        try {
            lista.agregarDespuesDe(c, b);
        } catch (Exception e) {
            System.out.println(e);
        }

        lista.agregarFinal(cc);

        PeriodPanel pp = new PeriodPanel(lista);
        PeriodPanel pp2 = new PeriodPanel(lista);
        PeriodPanel pp3 = new PeriodPanel(lista);
        PeriodPanel pp4 = new PeriodPanel(lista);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(pp, gbc);
        gbc.gridy = 1;
        panel.add(pp2, gbc);
        gbc.gridy = 2;
        panel.add(pp3, gbc);
        gbc.gridy = 3;
        panel.add(pp4, gbc);

        mainFrame.getContentPane().add(scroll);

        mainFrame.setLocationRelativeTo(mainFrame.getRootPane());
        mainFrame.setVisible(true);
        System.out.println(mainFrame.getContentPane().getSize());
    }

}
