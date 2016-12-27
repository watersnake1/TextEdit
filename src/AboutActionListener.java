import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/14/16.
 * when the about button is clicked, create a pop up with basic info
 */
public class AboutActionListener extends MenuBarActionListener {
    private JPanel panel;

    public AboutActionListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(panel, "Pez Editor V.0.0.2 \n All icons courtesy of flaticons.com and are property of their respective creators");
    }
}
