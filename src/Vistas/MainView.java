package Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import Pruebas.ParserTest;
import Pruebas.ScannerTest;
import Clases.Utilidades.Salida;
import java.io.*;

public class MainView {
    private JTextArea textArea1;
    private JTable table;
    private JTextArea textArea2;
    private File selectedFile;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }

    public MainView() {
        // Crear el marco principal
        JFrame frame = new JFrame("Main View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("    Archivo    ");
        JMenu menu2 = new JMenu("    Reportes    ");
        JMenu executeMenu = new JMenu("    Ejecutar    ");

        // Agregar submenús al primer ítem
        JMenuItem newFileItem = new JMenuItem("  Nuevo Archivo    ");
        newFileItem.addActionListener(e -> createNewFile());
        JMenuItem openFileItem = new JMenuItem("  Abrir    ");
        openFileItem.addActionListener(e -> selectFile());
        JMenuItem saveFileItem = new JMenuItem("  Guardar    ");
        saveFileItem.addActionListener(e -> saveFile());

        menu1.add(newFileItem);
        menu1.add(openFileItem);
        menu1.add(saveFileItem);

        // Agregar submenús al segundo ítem
        JMenuItem tokenReportItem = new JMenuItem("  Reporte de tokens    ");
        tokenReportItem.addActionListener(e -> generateTokenReport());
        JMenuItem errorReportItem = new JMenuItem("  Reporte de errores    ");
        errorReportItem.addActionListener(e -> generateErrorReport());

        menu2.add(tokenReportItem);
        menu2.add(errorReportItem);

        // Agregar acción al menú de ejecutar
        executeMenu.addMenuListener(new javax.swing.event.MenuListener() {
            @Override
            public void menuSelected(javax.swing.event.MenuEvent e) {
                executeTextAreaContent();
            }

            @Override
            public void menuDeselected(javax.swing.event.MenuEvent e) {}

            @Override
            public void menuCanceled(javax.swing.event.MenuEvent e) {}
        });

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(executeMenu);
        frame.setJMenuBar(menuBar);

        // Crear el panel principal con un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Crear el panel superior con un GridLayout para los dos cuadros
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));

        // Crear el primer cuadro de texto con etiqueta
        textArea1 = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("ENTRADA"), BorderLayout.NORTH);
        inputPanel.add(scrollPane1, BorderLayout.CENTER);
        topPanel.add(inputPanel);

        // Crear la tabla con etiqueta
        String[] columnNames = {"Token", "Fila", "Columna", "Tipo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane2 = new JScrollPane(table);
        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.add(new JLabel("REPORTE"), BorderLayout.NORTH);
        reportPanel.add(scrollPane2, BorderLayout.CENTER);
        topPanel.add(reportPanel);

        // Crear el tercer cuadro de texto con etiqueta y altura fija
        textArea2 = new JTextArea();
        JScrollPane scrollPane3 = new JScrollPane(textArea2);
        scrollPane3.setPreferredSize(new Dimension(800, 200));
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JLabel("SALIDA"), BorderLayout.NORTH);
        outputPanel.add(scrollPane3, BorderLayout.CENTER);

        // Agregar los paneles al panel principal
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        // Agregar el panel principal al marco
        frame.add(mainPanel);

        // Hacer visible el marco
        frame.setVisible(true);
    }

    private void createNewFile() {
        textArea1.setText("");
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea1.setText(content.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                bw.write(textArea1.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void generateTokenReport() {
        // Implementar lógica para generar reporte de tokens
    }

    private void generateErrorReport() {
        // Implementar lógica para generar reporte de errores
    }

    private void executeTextAreaContent() {
        try {
            // Clear the previous output
            Salida.salidaConsola.clear();

            // Get the content from textArea1
            String content = textArea1.getText();

            // Pass the content to ScannerTest and ParserTest
            ScannerTest.main(content);
            ParserTest.main(content);

            // Display the content of salidaPartida in textArea2
            StringBuilder output = new StringBuilder();
            for (String line : Salida.salidaConsola) {
                output.append(line).append("\n");
            }
            textArea2.setText(output.toString());

            // Update the table with tokens, rows, columns, and types
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0); // Clear existing rows
            for (int i = 0; i < Salida.tokens.size(); i++) {
                tableModel.addRow(new Object[]{
                        Salida.tokens.get(i),
                        Salida.filas.get(i),
                        Salida.columnas.get(i),
                        Salida.tipos.get(i)
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}