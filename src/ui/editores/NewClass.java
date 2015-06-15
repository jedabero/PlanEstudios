package ui.editores;

import io.FileCreator;
import io.FileParser;
import io.IO;
import io.TipoArchivoIncorrectoException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicArrowButton;
import matr.Asignatura;
import matr.Periodo;
import matr.Plan;
import matr.estudiante.Estudiante;
import ui.GUI;

/**
 *
 * @author Jedabero
 */
public class NewClass {

    public static void main3(String[] args) {
        Asignatura a = new Asignatura("123", "12", 12);
        AsignaturaEditor ae = new AsignaturaEditor(a, new Plan());
        int r = JOptionPane.showConfirmDialog(null, ae, "Title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (r == JOptionPane.OK_OPTION) {
            try {
                System.out.println(ae.getA());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "ERROR DE FORMATO", "Error en Valor de Nota", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "VACIO", "Error en Valor de Nota", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "VACIO", "Error en Valor de Nota", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            System.out.println("No");
        }

    }

    public static void main1(String[] args)
            throws IOException, ClassNotFoundException {
        FileParser fp = new FileParser(GUI.matrDir + "asd.pln");

        FileOutputStream fileOut = new FileOutputStream(GUI.matrDir + "media2.obj");
        ObjectOutputStream salida = new ObjectOutputStream(fileOut);
        salida.writeObject(fp.getPlan());
        salida.close();

        FileInputStream fileIn = new FileInputStream(GUI.matrDir + "media2.obj");
        ObjectInputStream entrada = new ObjectInputStream(fileIn);
        System.out.println(entrada.readObject());
        entrada.close();
    }

    public static void main2(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.err);
        }//
        JFileChooser jfc = new JFileChooser(GUI.matrDir);
        jfc.setMultiSelectionEnabled(false);
        System.out.println(IO.USER.s);
        System.out.println(jfc.showSaveDialog(null));
        File f = new File(jfc.getSelectedFile().getPath() + ".plan");
        try {
            if (f.createNewFile()) {
                System.out.println(f.exists());
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        try {
            //FileCreator fc = new FileCreator("qwerty", null, 1);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Editor de asignaturas");

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(250, 300);
        ventana.setLocationRelativeTo(ventana.getRootPane());
        ventana.setVisible(true);

        final String fppath = IO.USER.s + IO.FS.s + "matr" + IO.FS.s + "asd.plan";
        final Plan plan;
        FileParser fp = null;
        try {
            fp = new FileParser(fppath);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }

        if (null != fp) {
            plan = fp.getPlan();
        } else {
            plan = new Plan(new Estudiante("Jeison Berdugo", "1140845597", "Ingenieria de Sistemas"));
        }
        
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        final AsignaturaEditor ae = new AsignaturaEditor(plan);
        panel.add(ae, BorderLayout.CENTER);

        JButton siguiente = new JButton("Agregar");
        siguiente.setToolTipText("Agregar asignatura");
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Asignatura a = ae.getA();
                    plan.agregarAsignatura(a);
                    ae.simpleClean();
                } catch (Exception ex) {
                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        JButton finalizar = new JButton("Terminado");
        finalizar.setToolTipText("Finalizar la edicion del plan.");
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileCreator fileCreator = new FileCreator(fppath, plan, 0);
                } catch (TipoArchivoIncorrectoException ex) {
                }
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        bottomPanel.add(finalizar);
        bottomPanel.add(siguiente);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        ventana.getContentPane().add(panel);
        ventana.revalidate();

    }

}
