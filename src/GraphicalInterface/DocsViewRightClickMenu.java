package GraphicalInterface;

import javax.swing.*;

/**
 * Created by christian on 12/31/16.
 */
public class DocsViewRightClickMenu extends JPopupMenu {
    private JMenuItem view;
    private JMenuItem close;

    public DocsViewRightClickMenu() {
        super();
        view = new JMenuItem("view");
        close = new JMenuItem("close");
        add(view);
        add(close);
        setVisible(false);
    }
}
