package Generators;

import java.io.File;

public class Generator_Scanner {
    public static void main(String [] args) {
        jflex.Main.generate(new File("src/Analizers/Scanner.jflex"));
    }
}