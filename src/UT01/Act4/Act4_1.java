package UT01.Act4;

import java.io.*;

public class Act4_1 {

    public static void main(String[] args) {

        try {
            // Escribir los números en el fichero
            DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream("src/UT01.Act4/numeros.dat")
            );

            for (int i = 0; i < 100; i++) {
                dos.writeInt(i);
            }

            dos.close();

            // Leer los números del fichero
            DataInputStream dis = new DataInputStream(
                    new FileInputStream("src/UT01.Act4/numeros.dat")
            );

            for (int i = 0; i < 100; i++) {
                System.out.println(dis.readInt());
            }

            dis.close();

        } catch (IOException e) {
            System.out.println("Error al trabajar con el fichero.");
        }
    }
}