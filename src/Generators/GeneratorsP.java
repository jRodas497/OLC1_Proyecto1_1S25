package Generators;

public class GeneratorsP {
    public static void main(String[] args) {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/Analizers",
                    "-symbols",
                    "Terminal",
                    "-parser",
                    "Parser",
                    "src/Analizers/Parser.cup"
                });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}