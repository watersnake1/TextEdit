package ActionListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/19/16.
 */
public class PlainButtonActionListener implements java.awt.event.ActionListener {
    private JEditorPane editorPane;

    public PlainButtonActionListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editorPane.setFont(new Font(editorPane.getFont().getName(), Font.PLAIN, 14));
        System.out.println(editorPane.getFont());
    }
}
