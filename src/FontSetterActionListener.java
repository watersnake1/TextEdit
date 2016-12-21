import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by christian on 12/18/16.
 * set the font when a combo item is selected
 */
public class FontSetterActionListener implements java.awt.event.ActionListener {
    private JComboBox comboBox;
    private JEditorPane editorPane;
    private JRadioButton boldButton;
    private JRadioButton italicButton;

    public FontSetterActionListener(JComboBox comboBox, JEditorPane editorPane, JRadioButton boldButton, JRadioButton italicButton) {
        this.comboBox = comboBox;
        this.editorPane = editorPane;
        this.boldButton = boldButton;
        this.italicButton = italicButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(comboBox.getSelectedItem().toString());
        FontComboItem c = (FontComboItem) comboBox.getSelectedItem();
        editorPane.setFont(c.getFont());
        boldButton.setSelected(false);
        italicButton.setSelected(false);
        //set the font in the editor pane to the font associated with the string value in the combo item

    }
}
