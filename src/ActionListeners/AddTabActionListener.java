package ActionListeners;

import GraphicalInterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/27/16.
 */
public class AddTabActionListener implements java.awt.event.ActionListener {
    private JTabbedPane tabbedPane;
    private UserInterface userInterface;

    public AddTabActionListener(JTabbedPane tabbedPane, UserInterface userInterface) {
        this.tabbedPane = tabbedPane;
        this.userInterface = userInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //code seems to be different from that in the open action listeners
        JEditorPane editorPane = new JEditorPane();
        JScrollPane s = new JScrollPane(editorPane);
        tabbedPane.addTab("untitled", s);
        userInterface.getTabNames().add("untitled");
    }
}
