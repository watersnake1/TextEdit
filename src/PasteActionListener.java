import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by christian on 12/15/16.
 */
public class PasteActionListener extends MenuBarActionListener {
    private JEditorPane editorPane;

    public PasteActionListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String data = null;
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e1) {
            e1.printStackTrace();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //System.out.println(data);
        if (data != null) {
            editorPane.setText(editorPane.getText() + data);
        }
        else {
            editorPane.setText(editorPane.getText() + "");
        }
    }
}
