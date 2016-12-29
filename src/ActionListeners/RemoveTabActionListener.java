package ActionListeners;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by christian on 12/29/16.
 */
public class RemoveTabActionListener implements java.awt.event.ActionListener {
    private JTabbedPane tabbedPane;

    public RemoveTabActionListener(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
    }
}
