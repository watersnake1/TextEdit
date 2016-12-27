import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/19/16.
 */
public class RadioButtonActionListener implements java.awt.event.ActionListener {
    private JRadioButton radioButton;
    private JEditorPane editorPane;

    public RadioButtonActionListener(JEditorPane editorPane, JRadioButton radioButton) {
        this.radioButton = radioButton;
        this.editorPane = editorPane;
    }

    public Boolean isBold(JRadioButton radioButton) {
        String name = radioButton.getText();
        System.out.println(name);
        if (name.contains("Bold")) {
            return true;
        }
        else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isBold(radioButton)) {
            editorPane.setFont(new Font(editorPane.getFont().getName(), Font.BOLD, 14));
            System.out.println("bold selected");
        }
        else {
            editorPane.setFont(new Font(editorPane.getFont().getName(), Font.ITALIC, 14));
            System.out.println("Italic selected");
        }
    }
}
