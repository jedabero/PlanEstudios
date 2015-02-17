package ui;

import io.FileCreator;
import io.FileParser;
import io.IO;
import io.TipoArchivoIncorrectoException;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import matr.Plan;

/**
 *
 * @author jedabero
 */
public class GUI {

    private JFrame mainFrame;

    private PlanPanel ppanel;
    private JScrollPane scroll;

    public static final String matrDir = IO.USER.s + IO.FS.s + "matr" + IO.FS.s;

    public static boolean debugMode = false;
    
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.start();
    }

    private void start() {
        mainFrame = new JFrame("Matr");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(IO.OS.s);
        DisplayMode dm = mainFrame.getGraphicsConfiguration().getDevice().getDisplayMode();

        Dimension maxSize = new Dimension(dm.getWidth(), dm.getHeight());
        //System.out.println(maxSize);
        Dimension size;
        //TODO window stuff
        if (IO.OS.s.contains("Windows")) {
            size = new Dimension(1056, 688);
        } else if (IO.OS.s.contains("Linux")) {
            size = new Dimension(1050, 680);
        } else {
            int h = (int) (maxSize.width / 1.6);
            size = new Dimension(maxSize.width, h);
        }
        mainFrame.setSize(size);

        setSystemLandF();
        mainFrame.setLocationRelativeTo(mainFrame.getRootPane());
        mainFrame.setVisible(true);

        String fppath = IO.USER.s + IO.FS.s + "matr" + IO.FS.s + "asd.plan";
        FileParser fp;
        try {
            fp = new FileParser(fppath);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            fp = new FileParser();
        }

        Plan plan = fp.getPlan().reorganizarAsignaturas();

        ppanel = new PlanPanel(plan, PlanPanel.Orientacion.HORIZONTAL);
        //ppanel = new PlanPanel(fp.getPlan(), PlanPanel.Orientacion.VERTICAL);
        scroll = new JScrollPane(ppanel);
        mainFrame.getContentPane().add(scroll);
        mainFrame.revalidate();
        try {
            FileCreator fc = new FileCreator(fppath, plan, 0);
        } catch (TipoArchivoIncorrectoException ex) {
        }
    }

    private void setSystemLandF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.err);
        }
    }

}
