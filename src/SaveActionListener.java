import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by christian on 12/14/16.
 */
public class SaveActionListener extends MenuBarActionListener{
    private JEditorPane editorPane;
    private JPanel panel;
    private JPanel topJPanel;

    public SaveActionListener(JEditorPane editorPane, JPanel panel, JPanel topJPanel) {
        this.editorPane = editorPane;
        this.panel = panel;
        this.topJPanel = topJPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int status = fileChooser.showSaveDialog(panel);
            File target = fileChooser.getSelectedFile();
            FileWriter outBound = new FileWriter(target);
            outBound.write(editorPane.getText());
            outBound.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        topJPanel.updateUI();
    }
}
