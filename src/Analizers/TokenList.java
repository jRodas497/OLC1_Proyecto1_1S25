package Analizers;

import java.io.BufferedReader;
import java.io.StringReader;
import java_cup.runtime.Symbol;

public class TokenList {
    public static void listTokens(String input) {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new StringReader(input)));
            Symbol token;
            System.out.println("TOKEN" + " ".repeat(35 - "TOKEN".length()) + "LINE" + " ".repeat(6 - "LINE".length()) + "COLUMN" + " ".repeat(8 - "COLUMN".length()) + "TYPE");
            do {
                token = scanner.next_token();
                if (token.value != null) {
                    System.out.println(token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Analizers.Terminal.terminalNames[token.sym]);
                }
            } while (token.value != null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String input = "your input text here"; // Replace with actual input
        listTokens(input);
    }
}

