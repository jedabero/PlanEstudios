package ui;

import io.FileParser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import io.IO;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jedabero
 */
public class GUI {

    private JFrame mainFrame;

    private PlanPanel ppanel;
    private JScrollPane scroll;

    public static final String matrDir = IO.USER.p + IO.FS.p + "matr" + IO.FS.p;

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.start();
    }

    private void start() {
        mainFrame = new JFrame("Matr");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(IO.OS.p);
        DisplayMode dm = mainFrame.getGraphicsConfiguration().getDevice().getDisplayMode();

        Dimension maxSize = new Dimension(dm.getWidth(), dm.getHeight());
        //System.out.println(maxSize);
        Dimension size;
        //TODO window stuff
        if (IO.OS.p.contains("Windows")) {
            size = new Dimension(1056, 688);
        } else if (IO.OS.p.contains("Linux")) {
            size = new Dimension(1050, 680);
        } else {
            int h = (int) (maxSize.width / 1.6);
            size = new Dimension(maxSize.width, h);
        }
        mainFrame.setSize(size);

        setSystemLandF();
        mainFrame.setLocationRelativeTo(mainFrame.getRootPane());
        mainFrame.setVisible(true);

        String fppath = IO.USER.p + IO.FS.p + "matr" + IO.FS.p + "asd.plan";
        FileParser fp;
        try {
            fp = new FileParser(fppath);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            fp = new FileParser();
        }

        ppanel = new PlanPanel(fp.getPlan().reorganizarAsignaturas(), PlanPanel.Orientacion.HORIZONTAL);
        //ppanel = new PlanPanel(fp.getPlan(), PlanPanel.Orientacion.VERTICAL);
        scroll = new JScrollPane(ppanel);
        mainFrame.getContentPane().add(scroll);
        mainFrame.revalidate();

        //System.out.println(mainFrame.getContentPane().getSize());
    }

    private void setSystemLandF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.err);
        }
    }

}
