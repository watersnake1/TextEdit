import javax.swing.*;

/**
 * Created by christian on 12/29/16.
 */
public class TabbedPopUpMenu extends JPopupMenu {
    private JMenuItem closeTab;
    private JTabbedPane tabbedPane;

    public TabbedPopUpMenu(JTabbedPane tabbedPane) {
        super("current tab");
        closeTab = new JMenuItem("close this tab");
        closeTab.addActionListener(new RemoveTabActionListener(tabbedPane));
        this.add(closeTab);
        this.setVisible(false);
    }
}
