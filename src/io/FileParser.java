package io;

import java.io.File;
import java.util.regex.Pattern;

import matr.Asignatura;
import matr.Lista;
import matr.Periodo;

/**
 *
 * @author Jedabero
 */
public class FileParser {

    private Periodo actual;
    private Periodo anterior;

    public FileParser(File file) {

    }

    public FileParser(String pathName) {
        this(new File(pathName));
    }

    public Asignatura creaAsignatura(String linea) throws Exception {
        if (null != linea && linea.isEmpty()) {
            linea = linea.substring(1, linea.length()-1);
            String[] p = linea.split(":");
            int cred = Integer.parseInt(p[2]);
            Asignatura req = anterior.getLista().buscarAsignaturaPorCod(p[4]);
            Asignatura coreq = actual.getLista().buscarAsignaturaPorCod(p[5]);
            double calif = Double.parseDouble(p[6]);
            boolean matr = Boolean.parseBoolean(p[7]);
            Asignatura asign = new Asignatura(p[0], p[1], cred, p[3], req, coreq, calif);
            asign.setMatriculada(matr);
            return asign;
        } else {
            throw new Exception("Segmento vacio");
        }
    }

    public Periodo creaPeriodo(String linea) {
        if (null != linea && linea.isEmpty()) {
            linea = linea.substring(1, linea.length()-1);
            String[] p = linea.split("\n");
            Lista l = new Lista();
            for (String s : p) {
                try {
                    Asignatura a  = creaAsignatura(s);
                    l.agregarFinal(a);
                } catch (Exception e) { }
            }
            return (!l.vacio())? new Periodo(l) : null;
        } else {
            return null;
        }
    }

}
