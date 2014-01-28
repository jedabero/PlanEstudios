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

    private String contents;

    private static String ext = ".pln";
    
    public FileParser(String pathName) {
        if (pathName.endsWith(ext)) {
            
        }
        
        try {
            contents = readFile(pathName, Charset.defaultCharset());
        } catch (IOException ex) {
            
        }
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
            return new Estudiante(seg);
        } else {
            return null;
        }
    }

    public Plan creaPlan(String linea) {
        if (null != linea && !linea.isEmpty()) {
            String[] seg = linea.split("\n");
            Plan p = new Plan(new Estudiante(seg[1].split(":")));
            return p;
        } else {
            return null;
        }
    }

}
