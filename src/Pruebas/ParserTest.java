package Pruebas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import Clases.Abstractas.Instruccion;
import Clases.Entorno.Entorno;
import Lenguaje.Parser;
import Lenguaje.Scanner;

public class ParserTest {
    public static void main(String content) throws Exception {
        try {
            Scanner scanner = new Scanner(
                    new BufferedReader(
                            new StringReader(content)
                    )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            Entorno global = new Entorno("global");
            String salida_ = "";
            for (Instruccion instruccion : parser.sentencias) {
                try {
                    instruccion.jugar(global);
                    for (String salida : Clases.Utilidades.Salida.salidaConsola) {
                        salida_ += salida + "\n";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(salida_);
        } catch (Exception e) {
            System.err.println("Syntax error at character on input");
            e.printStackTrace();
            throw new Exception("Can't recover from previous error(s)");
        }
    }

    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder texto = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }
            br.close();
            fis.close();
            return texto.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}