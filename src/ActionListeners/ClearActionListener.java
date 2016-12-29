package ActionListeners;

import javax.swing.*;
import javax.swing.text.html.ObjectView;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/19/16.
 * Clears the editor when a button is clicked
 */
public class ClearActionListener implements java.awt.event.ActionListener {
    private Object pane;

    public ClearActionListener(Object pane) {
        this.pane = pane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pane instanceof JEditorPane) {
            ((JEditorPane) pane).setText("");
        }
        else if (pane instanceof JTextArea) {
            ((JTextArea) pane).setText("");
            JTextArea area = (JTextArea) pane;
            JFrame frame = (JFrame) SwingUtilities.windowForComponent(area.getParent());
            frame.dispose();
        }
        else {
            System.out.println("unsupported component given");
        }
    }
}
