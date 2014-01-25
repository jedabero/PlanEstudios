
package io;

import java.io.File;

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
    
    public Asignatura creaAsignatura(String linea) {
        String[] p = linea.split(":");
        int cred = Integer.parseInt(p[2]);
        double calif = Double.parseDouble(p[5]);
        boolean matr = Boolean.parseBoolean(p[6]);
        return null;
    }
    
    public Periodo creaPeriodo(Lista lista){
        return  null;
    }
    
}
