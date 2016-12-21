import java.awt.event.ActionEvent;

/**
 * Created by christian on 12/15/16.
 */
public class NewActionListener extends MenuBarActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        NewFileView newFileView = new NewFileView();
    }
}
