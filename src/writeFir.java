import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class writeFir {
    private  String FirReportId;

    protected void setFirReportId(String name) {
        FirReportId = "src/firs/" + name + ".txt";
    }

    //writing the fir in to the file and saving it
    private void writeToFile(String text) {
        try {
            FileWriter writer = new FileWriter(FirReportId, true);
            writer.write(text + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //the ui part


    protected void startUI() {

        JFrame frame = new JFrame("FIR Writer");

        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton button = new JButton("Save FIR");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();

                //her is the metho to call the write method
                writeToFile(text);
                // thia



                JOptionPane.showMessageDialog(frame, "FIR Saved");
            }
        });

        frame.add(scrollPane);
        frame.add(button, "South");

        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return ;
    }


}
