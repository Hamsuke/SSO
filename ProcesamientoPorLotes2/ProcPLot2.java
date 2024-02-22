import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import static java.nio.file.Files.copy;


public class ProcPLot2 {

    public static void EscribirArch(String cadena, String archivo) throws IOException {
        FileWriter escritura = new FileWriter(archivo, true);
        escritura.write(cadena + "\n");
        escritura.close();
    }

    private static String generar(String nombre){
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Random rand = new Random();
        String[] partes = nombre.split("\\.");
        char[] a = partes[0].toCharArray();
        StringBuilder out = new StringBuilder();

        for (char c : a) {
            int temp;
            String tmp = String.valueOf(c);
            if (pattern.matcher(tmp).matches()) {
                temp = rand.nextInt(10);
                out.append(temp);
            } else {
                out.append((char) (Math.random() * 26 + 'A'));
            }
        }
        return out.toString();
    }
    private static void modificar(String origen, String destino) throws IOException {
        File ArchO = new File(origen);
        File ArchN = new File(destino);

        FileReader lectura = new FileReader(ArchO);
        BufferedReader reader = new BufferedReader(lectura);
        String linea = reader.readLine();
        do {
            String escribir;
            if (linea != null) {
                escribir = generar(linea);
                EscribirArch(escribir, ArchN.getAbsolutePath());
                linea = reader.readLine();
            }else {
                lectura.close();
                copy(ArchO.toPath(), ArchN.toPath());
            }
        }while (linea != null);
    }
    private static void copiar(String orig, String dest) throws IOException {
        File rutaO = new File(orig);
        File rutaD = new File(dest);
        rutaD.mkdir();
        String[] archivos = rutaO.list();
        assert archivos != null;
        for (String archivo : archivos) {
            if (archivo.contains(".")) {
                File archN = new File(rutaD.getAbsolutePath() + "\\" + archivo);
                new File(archN.getAbsolutePath());
                String RutaN = rutaD.getAbsolutePath()+ "\\" + archivo;
                String RutaV = rutaO.getAbsolutePath() + "\\" + archivo;
                modificar(RutaV, RutaN);
            } else {
                StringBuilder subCarpetaD = new StringBuilder();
                String subCarpetaO = rutaO.getAbsolutePath() + "\\" + archivo;
                subCarpetaD.append(rutaD.getAbsolutePath()).append("\\").append(archivo);
                copiar(subCarpetaO, subCarpetaD.toString());
            }
        }

    }

    public static void main(String[] args) throws IOException {
        String origen;
        String destino;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la direccion del fichero");
        origen = "H:\\Escritorio\\Test\\Origen";
        //origen = scanner.next();
        destino = "H:\\Escritorio\\Test\\Destino";
        copiar(origen, destino);
    }
}
