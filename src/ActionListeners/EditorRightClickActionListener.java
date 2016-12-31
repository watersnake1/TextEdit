package ActionListeners;

import GraphicalInterface.RightClickMenu;
import GraphicalInterface.UserInterface;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by christian on 12/30/16.
 */
public class EditorRightClickActionListener implements MouseListener {
    private JEditorPane editorPane;
    private RightClickMenu rightClickMenu;
    private UserInterface userInterface;

    public EditorRightClickActionListener(UserInterface userInterface, RightClickMenu rightClickMenu) {
        this.userInterface = userInterface;
        this.rightClickMenu = rightClickMenu;
        editorPane = userInterface.getForegroundEditor();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            rightClickMenu.setVisible(true);
            rightClickMenu.show(editorPane, e.getX(), e.getY());
        }
        else if (SwingUtilities.isLeftMouseButton(e)) {
            rightClickMenu.setVisible(false);
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
