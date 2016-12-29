package ActionListeners;

import GraphicalInterface.UserInterface;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

/**
 * Created by christian on 12/27/16.
 */
public class TextInputListener implements DocumentListener {
    private JEditorPane editorPane;
    private UserInterface u;

    public TextInputListener(JEditorPane editorPane, UserInterface u) {
        this.editorPane = editorPane;
        this.u = u;
    }

    public char getLastChar(String str) {
        return str.charAt(str.length()-1);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println(getLastChar(editorPane.getText()));
        if (getLastChar(editorPane.getText()) == '{') {
            u.matchCurlyBraces();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("delete");
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
