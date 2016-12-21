import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by christian on 12/20/16.
 * enters a command into the system
 */
public class EnterButtonActionListener implements java.awt.event.ActionListener {
    private JTextField textField;

    public EnterButtonActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commandOutput = null;
        //need to text word by word from text field
        String[] command = textField.getText().split(" ");
        textField.setText("");
        CommandOutputView c = new CommandOutputView();
        Process p = null;
        try {
            p = new ProcessBuilder(command).start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));
        System.out.println("Here is the standard output of the command:\n");
        try {
            while ((commandOutput = stdInput.readLine()) != null) {
                c.getCommandField().setText(c.getCommandField().getText()+commandOutput + "\n");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
