package ActionListeners;
import GraphicalInterface.UserInterface;

import java.awt.event.MouseEvent;

/**
 * Created by christian on 12/24/16.
 */
public class BottomToolBarActionListener implements java.awt.event.MouseListener {
    private int status;
    private UserInterface userInterface;

    public BottomToolBarActionListener(int status, UserInterface userInterface) {
        this.status = status;
        this.userInterface = userInterface;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (status) {
            case 0:
                System.out.println("compile feature coming soon");
                //this needs to be changed
                if (userInterface.getCurrentLang().equals("java")) {
                    String[] compileCommand = new String[]{"javac", userInterface.getTarget().toString()};
                    System.out.println(compileCommand);
                }
                break;
            case 1:
                System.out.println("run feature coming soon");
                break;
            default:
                break;
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

    public void compile(String lang) {

    }
}
