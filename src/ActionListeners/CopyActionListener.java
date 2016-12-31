package ActionListeners;

import ActionListeners.MenuBarActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

/**
 * Created by christian on 12/14/16.
 * copies text to the system clipboard
 */
public class CopyActionListener extends MenuBarActionListener {
    private JEditorPane editorPane;
    private JTextArea textArea;
    private boolean isEditPane;

    public CopyActionListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
        textArea = null;
        isEditPane = true;
    }

    public CopyActionListener(JTextArea textArea) {
        this.textArea = textArea;
        editorPane = null;
        isEditPane = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textToCopy;
        if (isEditPane) {
            textToCopy = editorPane.getSelectedText();
        }
        else {
            textToCopy = textArea.getText();
        }
        StringSelection textSelection = new StringSelection(textToCopy);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(textSelection, null);

    }
}
