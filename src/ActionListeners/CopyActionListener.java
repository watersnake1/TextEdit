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

    public CopyActionListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textToCopy = editorPane.getSelectedText();
        StringSelection textSelection = new StringSelection(textToCopy);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(textSelection, null);

    }
}
