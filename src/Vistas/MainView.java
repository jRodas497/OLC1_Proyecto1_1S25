package Vistas;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import Pruebas.ParserTest;
import Pruebas.ScannerTest;
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
        frame.setSize(800, 700);
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
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
                {"Data 1", "Data 2", "Data 3"},
                {"Data 4", "Data 5", "Data 6"},
                {"Data 7", "Data 8", "Data 9"}
        };
        table = new JTable(data, columnNames);
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
        if (selectedFile != null) {
            try {
                // Redirigir la salida estándar a textArea2
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);

                // Ejecutar ParserTest y ScannerTest
                ParserTest.main(new String[]{selectedFile.getAbsolutePath()});
                ScannerTest.main(new String[]{selectedFile.getAbsolutePath()});

                // Restaurar la salida estándar y mostrar el resultado en textArea2
                System.out.flush();
                System.setOut(old);
                textArea2.setText(baos.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}