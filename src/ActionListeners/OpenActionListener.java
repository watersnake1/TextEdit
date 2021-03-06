package ActionListeners;

import ActionListeners.MenuBarActionListener;
import GraphicalInterface.RightClickMenu;
import GraphicalInterface.UserInterface;

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
    private JEditorPane edit;
    private UserInterface userInterface;

    public OpenActionListener(JEditorPane editorPane, JPanel panel, File target, JFrame frame, JTabbedPane tabbedPane,
                              UserInterface userInterface) {
        this.editorPane = editorPane;
        this.panel = panel;
        this.target = target;
        this.frame =  (JFrame) frame;
        this.tabbedPane = tabbedPane;
        this.userInterface = userInterface;
        edit = new JEditorPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(new File("."));
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
        userInterface.getTabNames().add(target.getName());
        userInterface.DocumentViewSetUp();

    }
}
