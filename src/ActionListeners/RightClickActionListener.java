package ActionListeners;

import GraphicalInterface.TabbedPopUpMenu;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by christian on 12/29/16.
 */
public class RightClickActionListener implements MouseListener {
    private TabbedPopUpMenu popUpMenu;
    private JTabbedPane invoker;

    public RightClickActionListener(TabbedPopUpMenu popUpMenu, JTabbedPane invoker) {
        this.popUpMenu = popUpMenu;
        this.invoker = invoker;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            popUpMenu.setVisible(true);
            popUpMenu.show(invoker, e.getX(), e.getY());
        }
        else if (SwingUtilities.isLeftMouseButton(e)) {
            popUpMenu.setVisible(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
