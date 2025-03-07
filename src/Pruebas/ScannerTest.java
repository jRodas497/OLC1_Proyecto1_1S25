package Pruebas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import Clases.Errores.ErrorLexico;
import Clases.Utilidades.Salida;
import Lenguaje.Scanner;
import java_cup.runtime.Symbol;
public class ScannerTest {
    public static void main(String content) throws Exception {
        try {
            Scanner scanner = new Scanner(
                    new BufferedReader(
                            new StringReader(content)
                    )
            );
            Symbol token;
            Salida.tokens.clear();
            Salida.filas.clear();
            Salida.columnas.clear();
            Salida.tipos.clear();

            do {
                token = scanner.next_token();
                if (token.value != null) {
                    Salida.tokens.add(token.value.toString());
                    Salida.filas.add(token.left);
                    Salida.columnas.add(token.right);
                    Salida.tipos.add(Lenguaje.Terminal.terminalNames[token.sym]);
                }
            } while (token.value != null);

            for (ErrorLexico error : scanner.getErroresLexicos()) {
                Salida.salidaConsola.add(error.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
            fis.close();
            return texto;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}