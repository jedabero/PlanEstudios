package matr;

import io.FileParser;
import io.IO;

import java.io.IOException;

/**
 *
 * @author jedabero
 */
public class Matr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         Plan plan = new Plan(new Estudiante("Jeison Berdugo", "1234", "Ing. Sist."));
         Periodo p1 = new Periodo("I");
         Periodo p2 = new Periodo("II");
         Periodo p3 = new Periodo("III");
        
         Asignatura a = new Asignatura("Matematicas", "10001", 2, "I", null, null, 4);
         Asignatura b = new Asignatura("Matematicas 2", "10002", 2, "II", a, null, 4);
         Asignatura c = new Asignatura("Matematicas 3", "100T3", 3, "III", b, null, 0);
         Asignatura cc = new Asignatura("Matematicas Lab", "100L2", 2, "III", null, c, 0);

         p1.agregarFinal(a);
         plan.agregarUltimo(p1);
         p2.agregarFinal(b);
         plan.agregarUltimo(p2);
         p3.agregarFinal(c);
         p3.agregarFinal(cc);
         plan.agregarUltimo(p3);
        
         System.out.println(plan);//*/

        ///*
        FileParser fp;
        try {
            fp = new FileParser(IO.USER.p + IO.FS.p + "matr" + IO.FS.p + "asd.pln");
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
            fp = null;
        }//*/

    }

}
