package ActionListeners;

import ActionListeners.MenuBarActionListener;

import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/14/16.
 */
public class QuitActionListener extends MenuBarActionListener {

    public QuitActionListener() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
