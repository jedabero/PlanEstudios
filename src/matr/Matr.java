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

        Periodo p = new Periodo("VI");

        p.agregarCabecera(a);
        p.agregarCabecera(b);
        try {
            p.agregarDespuesDe(c, b);
        } catch (Exception e) {
            System.out.println(e);
        }

        p.agregarFinal(cc);

        Plan plan = new Plan(
//        /*
            new Estudiante("Jeison Berdugo", "1234", "Ing. Sist.")
//        */
            );
        
        plan.agregarPrimero(p);
        System.out.println(plan);

    }

}
