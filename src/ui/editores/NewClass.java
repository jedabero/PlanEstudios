
package ui.editores;

import io.FileParser;
import io.IO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import matr.Asignatura;
import matr.Periodo;
import ui.GUI;

/**
 *
 * @author Jedabero
 */
public class NewClass {

    public static void main2(String[] args) {
        Asignatura a = new Asignatura("123", "12", 12);
        AsignaturaEditor ae = new AsignaturaEditor(a,new Periodo());
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
            throws FileNotFoundException, IOException, ClassNotFoundException {
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
    
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.err);
        }//
        JFileChooser jfc = new JFileChooser(GUI.matrDir);
        jfc.setMultiSelectionEnabled(false);
        System.out.println(IO.USER.p);
        System.out.println(jfc.showSaveDialog(null));
        File f = new File(jfc.getSelectedFile().getPath()+".plan");
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
}
