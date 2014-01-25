
package io;

import matr.Asignatura;
import matr.Lista;
import matr.Periodo;

/**
 *
 * @author Jedabero
 */
public class FileParser {
    
    
    
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
