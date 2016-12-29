package GraphicalInterface;

import ActionListeners.ClearActionListener;

import javax.swing.*;
import java.awt.Dimension;

/**
 * Created by christian on 12/19/16.
 */
public class CommandOutputView  extends JFrame {
    private JPanel rootCommandPanel;
    private JTextArea commandField;
    private JButton clearButton;
    private JButton exitButton;
    private JToolBar commandToolBar;
    private JButton copyOutputButton;

    public CommandOutputView() {
        super("Command Output");
        setContentPane(rootCommandPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitButton.addActionListener(new ClearActionListener(getCommandField()));
        pack();
    }

    public JTextArea getCommandField() {
        return commandField;
    }
}
