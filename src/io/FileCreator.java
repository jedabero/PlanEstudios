package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import matr.Plan;

/**
 *
 * @author Jedabero
 */
public class FileCreator {

    private Plan plan;

    public static final int OBJ = 1;
    public static final int TXT = 0;

    public FileCreator(String pathName, Plan plan, int tipo) throws TipoArchivoIncorrectoException {
        this.plan = plan;
        switch (tipo) {
            case OBJ:
                if (pathName.endsWith(IO.EXTO.s)) {
                    //Nada
                } else if (pathName.endsWith(IO.EXT.s)) {
                    throw new TipoArchivoIncorrectoException("Tipo equivocado de archivo.");
                } else if (pathName.lastIndexOf(".") > pathName.length() - 5) {
                    throw new TipoArchivoIncorrectoException("Tipo de extension equivocado.");
                } else {
                    pathName += IO.EXTO.s;
                }
                crearObj(pathName);
                break;
            case TXT:
                if (pathName.endsWith(IO.EXT.s)) {
                    //Nada
                } else if (pathName.endsWith(IO.EXTO.s)) {
                    throw new TipoArchivoIncorrectoException("Tipo equivocado de archivo.");
                } else if (pathName.lastIndexOf(".") > pathName.length() - 5) {
                    throw new TipoArchivoIncorrectoException("Tipo de extension equivocado.");
                } else {
                    pathName += IO.EXT.s;
                }
                crearTexto(pathName);
                break;
            default:
                throw new TipoArchivoIncorrectoException();
        }
    }

    private void crearTexto(String pathName) {

        FileWriter fichero = null;
        PrintWriter pw;
        try {
            fichero = new FileWriter(pathName);
            pw = new PrintWriter(fichero);
            pw.print(plan);

        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace(System.err);
            }
        }

    }

    private void crearObj(String pathName) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(pathName);
        } catch (FileNotFoundException ex) {
            fileOut = null;
            ex.printStackTrace(System.err);
        }
        try (ObjectOutputStream salida = new ObjectOutputStream(fileOut)) {
            salida.writeObject(plan);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
