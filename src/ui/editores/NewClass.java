
package ui.editores;

import javax.swing.JOptionPane;
import matr.Asignatura;
import matr.Periodo;

/**
 *
 * @author Jedabero
 */
public class NewClass {

    public static void main(String[] args) {
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
}
