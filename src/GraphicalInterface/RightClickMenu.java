package GraphicalInterface;

import ActionListeners.CopyActionListener;
import ActionListeners.CutActionListener;
import ActionListeners.PasteActionListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

/**
 * Created by christian on 12/30/16.
 */
public class RightClickMenu extends JPopupMenu {
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JEditorPane editorPane;

    public RightClickMenu(JEditorPane editorPane) {
        super("right click");
        this.editorPane = editorPane;
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        this.add(cut, copy);
        this.add(paste);
        cut.addActionListener(new CutActionListener(editorPane));
        copy.addActionListener(new CopyActionListener(editorPane));
        paste.addActionListener(new PasteActionListener(editorPane));
        setVisible(false);
    }
}
