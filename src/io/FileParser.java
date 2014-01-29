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
    private Plan plan;

    private String contents;

    public FileParser(String pathName) throws IOException {
        if (pathName.endsWith(IO.EXT.p)) {
            try {
                contents = readFile(pathName, Charset.defaultCharset());
                init();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        } else {
            throw new IOException("No se reconoce " + pathName);
        }
    }

    private void init() {
        creaPlan(contents);
        System.out.println(plan);
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public void creaPlan(String linea) {
        if (null != linea && !linea.isEmpty()) {
            int fl1 = linea.indexOf(IO.FL.p);
            int fl2 = linea.indexOf(IO.FL.p, fl1 + 1);
            String q = linea.substring(fl1, fl2).trim();
            plan = new Plan(creaEstudiante(q));
            linea = linea.substring(fl2 + 1).trim();
            String[] seg = linea.split("¬");
            for (String string : seg) {
                Periodo period = creaPeriodo(string);
                if (null != period) {
                    plan.agregarUltimo(period);
                }
            }
        } else {

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

    public Periodo creaPeriodo(String linea) {
        if (null != linea && !linea.isEmpty()) {
            int fl1 = linea.indexOf(IO.FL.p);
            String q = linea.substring(0, fl1).trim();
            linea = linea.substring(fl1, linea.length()-1).trim();
            String[] p = linea.split(IO.FL.p);
            actual = new Periodo(q);
            for (String s : p) {
                try {
                    actual.agregarFinal(creaAsignatura(s.trim()));
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
            return actual;
        } else {
            return null;
        }
    }

    public Asignatura creaAsignatura(String linea) throws Exception {
        if (null != linea && !linea.isEmpty() && linea.length() > 10) {
            linea = linea.substring(1, linea.length() - 2);
            String[] p = linea.split(":");
            if (p.length == 8) {
                int cred = Integer.parseInt(p[2]);
                Asignatura req = (plan.vacio() || p[4].equals("No")) ? null : plan.getUlt().buscarAsignaturaPorCod(p[4]);
                Asignatura coreq = (plan.vacio() || p[5].equals("No")) ? null : actual.buscarAsignaturaPorCod(p[5]);
                double calif = Double.parseDouble(p[6]);
                boolean matr = Boolean.parseBoolean(p[7]);
                Asignatura asign = new Asignatura(p[0], p[1], cred, p[3], req, coreq, calif);
                asign.setMatriculada(matr);
                return asign;
            } else {
                throw new Exception("Segmento erroneo");
            }
        } else {
            throw new Exception("Segmento vacio o incompleto: "+linea);
        }
    }

}
