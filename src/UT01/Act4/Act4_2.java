package UT01.Act4;

import java.io.*;

public class Act4_2 {
    static void main() {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/UT01.Act4/alumno.dat"));
            dos.writeUTF("Cristian");
            dos.writeInt(19);
            dos.writeDouble(8.25);

            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream("src/UT01.Act4/alumno.dat"));
            String nombre = dis.readUTF();
            int edad = dis.readInt();
            double notaMedia = dis.readDouble();

            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Nota media: " + notaMedia);
            dis.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
