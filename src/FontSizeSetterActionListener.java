
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/18/16.
 */
public class FontSizeSetterActionListener implements java.awt.event.ActionListener {
    private JEditorPane editorPane;
    private FontSizeComboItem comboItem;
    private JComboBox fontComboBox;
    private JComboBox fontSizeComboBox;

    public FontSizeSetterActionListener(JEditorPane editorPane, JComboBox fontComboBox, JComboBox fontSizeComboBox) {
        this.editorPane = editorPane;
        this.fontComboBox = fontComboBox;
        this.fontSizeComboBox = fontSizeComboBox;
        comboItem = (FontSizeComboItem) fontSizeComboBox.getSelectedItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FontComboItem c = (FontComboItem) fontComboBox.getSelectedItem();
        int size = comboItem.getSize();
        c.setSize(comboItem.getSize());
        editorPane.setFont(c.getFont());
    }
}
