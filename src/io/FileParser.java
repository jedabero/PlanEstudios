package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import matr.Asignatura;
import matr.estudiante.Estudiante;
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

    public FileParser(String pathName)
            throws IOException, ClassNotFoundException {
        if (pathName.endsWith(IO.EXT.s)) {
            try {
                contents = readFile(pathName, Charset.defaultCharset());
                init();
            } catch (IOException ex) {
                plan = new Plan();
                ex.printStackTrace(System.err);
            }
        } else if (pathName.endsWith(IO.EXTO.s)) {
            FileInputStream fileIn = new FileInputStream(pathName);
            try (ObjectInputStream entrada = new ObjectInputStream(fileIn)) {
                plan = (Plan) entrada.readObject();
            }
        } else {
            plan = new Plan();
            throw new IOException("No se reconoce " + pathName);
        }
    }

    public FileParser() {
        plan = new Plan();
    }

    private void init() {
        creaPlan(contents);
        //System.out.println(plan);
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public void creaPlan(String linea) {
        if (null != linea && !linea.isEmpty()) {
            int fl1 = linea.indexOf(IO.LS.s);
            int fl2 = linea.indexOf(IO.LS.s, fl1 + 1);
            String q = linea.substring(fl1, fl2).trim();
            plan = new Plan(creaEstudiante(q));
            linea = linea.substring(fl2 + 1).trim();
            String[] seg = linea.split("\\(");
            for (String string : seg) {
                Periodo period = creaPeriodo(string);
                if (null != period) {
                    plan.agregar(period);
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
            int fl1 = linea.indexOf(IO.LS.s);
            String q = linea.substring(0, fl1).trim();
            linea = linea.substring(fl1, linea.length()).trim();
            String[] p = linea.split(IO.LS.s);
            actual = new Periodo(q, plan);
            for (String s : p) {
                try {
                    actual.agregar(creaAsignatura(s.trim()));
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
            linea = linea.substring(1, linea.length() - 1);
            String[] p = linea.split(":");
            if (p.length == 9) {
                int cred = Integer.parseInt(p[2]);
                Asignatura req = (plan.vacio() || p[5].equals("No")) ? null : plan.buscarAsignaturaPorCodigo(p[5]);
                Asignatura coreq = (plan.vacio() || p[6].equals("No")) ? null : actual.buscarAsignaturaPorCodigo(p[6]);
                double calif = Double.parseDouble(p[7]);
                boolean matr = Boolean.parseBoolean(p[8]);
                Asignatura asign = new Asignatura(p[0], p[1], cred, p[3], p[4], req, coreq, calif);
                asign.setMatriculada(matr);
                return asign;
            } else {
                throw new Exception("Segmento erroneo");
            }
        } else {
            throw new Exception("Segmento vacio o incompleto: " + linea);
        }
    }

    public Plan getPlan() {
        return plan;
    }

}
