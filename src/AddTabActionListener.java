import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/27/16.
 */
public class AddTabActionListener implements java.awt.event.ActionListener {
    private JTabbedPane tabbedPane;

    public AddTabActionListener(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editorPane = new JEditorPane();
        tabbedPane.addTab("untitled", editorPane);
    }
}
