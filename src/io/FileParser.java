package io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import matr.Asignatura;
import matr.Estudiante;
import matr.Periodo;
import matr.Plan;

/**
 *
 * @author Jedabero
 */
public class FileParser {

    private Periodo actual;
    private Periodo anterior;

    private Plan plan;

    private String contents;

    public FileParser(String pathName) throws IOException {
        if (pathName.endsWith(IO.EXT.p)) {
            System.out.println(IO.SEPARATOR.p);
            try {
                contents = readFile(pathName, Charset.defaultCharset());
                init();
                System.out.println(plan);
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        } else {
            throw new IOException("No se reconoce " + pathName);
        }
    }

    private void init() {
        plan = creaPlan(contents);
    }
    
    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public Asignatura creaAsignatura(String linea) throws Exception {
        if (null != linea && linea.isEmpty()) {
            linea = linea.substring(1, linea.length() - 1);
            String[] p = linea.split(":");
            int cred = Integer.parseInt(p[2]);
            Asignatura req = anterior.buscarAsignaturaPorCod(p[4]);
            Asignatura coreq = actual.buscarAsignaturaPorCod(p[5]);
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
        if (null != linea && !linea.isEmpty()) {
            linea = linea.substring(1, linea.length() - 1);
            String[] p = linea.split("\n");
            Periodo l = new Periodo(p[0]);
            for (String s : p) {
                try {
                    Asignatura a = creaAsignatura(s);
                    l.agregarFinal(a);
                } catch (Exception e) {
                }
            }
            return l;
        } else {
            return null;
        }
    }

    public Estudiante creaEstudiante(String linea) {
        if (null != linea && !linea.isEmpty()) {
            String[] seg = linea.split(":");
            try {
                return new Estudiante(seg);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return null;
            }
        } else {
            return null;
        }
    }

    public Plan creaPlan(String linea) {
        if (null != linea && !linea.isEmpty()) {
            String q = linea.substring(linea.indexOf("\n") + 1);
            System.out.println(q);
            System.out.println(linea);
            String[] seg = linea.split("\n");
            Plan p = new Plan(creaEstudiante(seg[1]));
            return p;
        } else {
            return null;
        }
    }

}
