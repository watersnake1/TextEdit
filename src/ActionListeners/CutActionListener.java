package ActionListeners;

import ActionListeners.MenuBarActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/15/16.
 * copies text to the system clipboard but also deletes that text from editor
 */
public class CutActionListener extends MenuBarActionListener {
    private JEditorPane editorPane;

    public CutActionListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textToCopy = editorPane.getSelectedText();
        StringSelection textSelection = new StringSelection(textToCopy);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(textSelection, null);
        editorPane.replaceSelection("");
    }
}
