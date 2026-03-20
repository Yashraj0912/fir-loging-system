import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileViewer extends JFrame {

    private JTextArea textArea;

    // Constructor (Parameterized → OOP)
    public FileViewer(String folderPath, String fileName) {
        setTitle("File Viewer - " + fileName);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Load file content
        loadFile(folderPath, fileName);

        setVisible(true);
    }

    // Method to read file
    private void loadFile(String folderPath, String fileName) {
        String str = folderPath + "/" + fileName + ".txt";
        File file = new File(folderPath, fileName + ".txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            textArea.setText("Error: File not found!");
        } catch (IOException e) {
            textArea.setText("Error reading file!");
        }
    }
}