package ActionListeners;

import ActionListeners.MenuBarActionListener;
import GraphicalInterface.FileTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by christian on 12/14/16.
 */
public class SaveActionListener extends MenuBarActionListener {
    private JEditorPane editorPane;
    private JPanel panel;
    private JPanel topJPanel;
    private JTabbedPane tabbedPane;
    private FileTree fileTree;

    public SaveActionListener(JEditorPane editorPane, JPanel panel, JPanel topJPanel, JTabbedPane tabbedPane, FileTree fileTree) {
        this.editorPane = editorPane;
        this.panel = panel;
        this.topJPanel = topJPanel;
        this.tabbedPane = tabbedPane;
        this.fileTree = fileTree;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser(new File("."));
            int status = fileChooser.showSaveDialog(panel);
            File target = fileChooser.getSelectedFile();
            FileWriter outBound = new FileWriter(target);
            JScrollPane s = (JScrollPane) tabbedPane.getComponent(tabbedPane.getSelectedIndex());
            JEditorPane x = (JEditorPane) s.getViewport().getView();
            outBound.write(x.getText());
            outBound.close();
            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), target.getName());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        topJPanel.updateUI();
        //fileTree.addNodes(null, new File(".")); not a public method
        fileTree.updateUI();
    }
}
