package io;

import java.io.BufferedWriter;
import java.io.File;
import matr.Plan;

/**
 *
 * @author Jedabero
 */
public class FileCreator {

    private Plan plan;

    public static final int OBJ = 1; 
    public static final int TXT = 0; 
    
    public FileCreator(String pathName, Plan plan, int tipo) throws Exception {
        this.plan = plan;
        switch(tipo) {
            case OBJ:
                if(pathName.endsWith(IO.EXTO.p)) {
                    //Nada
                } else if (pathName.endsWith(IO.EXT.p)) {
                    throw new Exception("Tipo equivocado de archivo.");
                } else if (pathName.lastIndexOf(".")>pathName.length()-5) {
                    throw new Exception("Tipo de extension equivocado.");
                } else {
                    pathName += IO.EXTO.p;
                }
                crearObj();
                break;
            case TXT:
                if(pathName.endsWith(IO.EXT.p)) {
                    
                }
                crearTexto();
                break;
            default:
                break;
        }
        
    }
    
    private void crearTexto() {
        
    }
    
    private void crearObj() {
        
    }
    
    
}
