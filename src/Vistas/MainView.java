package Vistas;

import javax.swing.*;
import java.awt.*;

public class MainView {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Main View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Item 1");
        JMenu menu2 = new JMenu("Item 2");
        JMenu menu3 = new JMenu("Item 3");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        frame.setJMenuBar(menuBar);

        // Crear el panel principal con un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear el panel superior con un GridLayout para los dos cuadros
        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        // Crear el primer cuadro de texto
        JTextArea textArea1 = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        topPanel.add(scrollPane1);

        // Crear la tabla
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
            {"Data 1", "Data 2", "Data 3"},
            {"Data 4", "Data 5", "Data 6"},
            {"Data 7", "Data 8", "Data 9"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane2 = new JScrollPane(table);
        topPanel.add(scrollPane2);

        // Crear el tercer cuadro de texto
        JTextArea textArea2 = new JTextArea();
        JScrollPane scrollPane3 = new JScrollPane(textArea2);

        // Agregar los paneles al panel principal
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane3, BorderLayout.SOUTH);

        // Agregar el panel principal al marco
        frame.add(mainPanel);

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
