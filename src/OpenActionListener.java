import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by christian on 12/14/16.
 */
public class OpenActionListener extends MenuBarActionListener {
    private JEditorPane editorPane;
    private JPanel panel;
    private File target;
    private JFrame frame;

    public OpenActionListener(JEditorPane editorPane, JPanel panel, File target, JFrame frame) {
        this.editorPane = editorPane;
        this.panel = panel;
        this.target = target;
        this.frame =  (JFrame) frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(panel);
        target = fileChooser.getSelectedFile();
        frame.setTitle(target.getName());
        FileReader in = null;
        try {
            in = new FileReader(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        char[] buffer = new char[100000000];
        int n = 0;
        try {
            n = in.read(buffer);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String text = new String(buffer, 0, n);
        editorPane.setText(text);
        try {
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
