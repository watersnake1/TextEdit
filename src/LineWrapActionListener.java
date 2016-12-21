import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/19/16.
 */
public class LineWrapActionListener implements java.awt.event.ActionListener {
    private JEditorPane editorPane;
    private JRadioButton radioButton;

    public LineWrapActionListener(JEditorPane editorPane, JRadioButton radioButton) {
        this.editorPane = editorPane;
        this.radioButton = radioButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (radioButton.isSelected()) {
            String text = editorPane.getText();
            Font font = editorPane.getFont();
            editorPane.setContentType("text/html");
            System.out.println("set to html mode");
            editorPane.setFont(font);
            editorPane.setText(text);

        }
        else if (!radioButton.isSelected()) {
            String text = editorPane.getText();
            editorPane.setContentType("text/plain");
            System.out.println("set to plaintext mode");
            editorPane.setText(text);
        }
    }
}
