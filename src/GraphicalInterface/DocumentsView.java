package GraphicalInterface;
import javax.swing.*;
import java.awt.*;

/**
 * Created by christian on 12/31/16.
 */
public class DocumentsView extends JPanel {
    private String title;

    public DocumentsView(String title) {
        setVisible(true);
        setPreferredSize(new Dimension(150, 700));
        add(new JLabel(title));
    }


}
