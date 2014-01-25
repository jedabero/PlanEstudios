package matr;

/**
 *
 * @author jedabero
 */
public class Matr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Asignatura a = new Asignatura("Matematicas", "10001", 2, "I", null, null, 6);
        Asignatura b = new Asignatura("Matematicas 2", "10002", 2, "II", a, null, 5.95);
        Asignatura c = new Asignatura(true, "III", "Matematicas 3", "100T3", 2, b, null);
        Asignatura cc = new Asignatura(true, "III", "Matematicas Lab", "100L3", 1, null, c);

        Lista lista = new Lista();

        lista.agregarCabecera(a);
        lista.agregarCabecera(b);
        try {
            lista.agregarDespuesDe(c, b);
        } catch (Exception e) {
            System.out.println(e);
        }

        lista.agregarFinal(cc);

        Periodo p = new Periodo(lista);
        System.out.println(p.getTotalCreditos() + "<--Cred");
        System.out.println(p.getTotalAsignaturas() + "<--Asign");
        System.out.println(p.getPromedio() + "<--Promedio");

        System.out.println(p);
        
    }

}
