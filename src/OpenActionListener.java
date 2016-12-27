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
    private JTabbedPane tabbedPane;

    public OpenActionListener(JEditorPane editorPane, JPanel panel, File target, JFrame frame, JTabbedPane tabbedPane) {
        this.editorPane = editorPane;
        this.panel = panel;
        this.target = target;
        this.frame =  (JFrame) frame;
        this.tabbedPane = tabbedPane;
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
        JEditorPane edit = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(edit);
        if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("untitled")) {
            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), target.getName());
            editorPane.setText(text);
        }
        else {
            tabbedPane.addTab(target.getName(), scrollPane);
            edit.setText(text);
        }
        try {
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
