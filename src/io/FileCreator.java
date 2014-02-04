package io;

import java.io.BufferedWriter;
import java.io.File;
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

    public FileCreator(String pathName, Plan plan, int tipo) throws Exception {
        this.plan = plan;
        switch (tipo) {
            case OBJ:
                if (pathName.endsWith(IO.EXTO.p)) {
                    //Nada
                } else if (pathName.endsWith(IO.EXT.p)) {
                    throw new Exception("Tipo equivocado de archivo.");
                } else if (pathName.lastIndexOf(".") > pathName.length() - 5) {
                    throw new Exception("Tipo de extension equivocado.");
                } else {
                    pathName += IO.EXTO.p;
                }
                crearObj(pathName);
                break;
            case TXT:
                if (pathName.endsWith(IO.EXT.p)) {
                    //Nada
                } else if (pathName.endsWith(IO.EXTO.p)) {
                    throw new Exception("Tipo equivocado de archivo.");
                } else if (pathName.lastIndexOf(".") > pathName.length() - 5) {
                    throw new Exception("Tipo de extension equivocado.");
                } else {
                    pathName += IO.EXT.p;
                }
                crearTexto(pathName);
                break;
            default:
                throw new Exception("Tipo incorrecto");
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

    private void crearObj(String pathName) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(pathName);
        try (ObjectOutputStream salida = new ObjectOutputStream(fileOut)) {
            salida.writeObject(plan);
        }
    }

}
