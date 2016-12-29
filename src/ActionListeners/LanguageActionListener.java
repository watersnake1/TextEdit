package ActionListeners;

import GraphicalInterface.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/25/16.
 */
public class LanguageActionListener implements java.awt.event.ActionListener {
    private UserInterface userInterface;
    private JComboBox comboBox;

    public LanguageActionListener(UserInterface userInterface, JComboBox comboBox) {
        this.userInterface = userInterface;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.setCurrentLang((String) comboBox.getSelectedItem().toString());
    }
}
