package ActionListeners;

import GraphicalInterface.FontComboItem;
import GraphicalInterface.UserInterface;

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
    private UserInterface userInterface;

    public FontSetterActionListener(JComboBox comboBox, UserInterface userInterface, JRadioButton boldButton, JRadioButton italicButton) {
        this.comboBox = comboBox;
        this.userInterface = userInterface;
        this.boldButton = boldButton;
        this.italicButton = italicButton;
        editorPane = userInterface.getForegroundEditor();
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
