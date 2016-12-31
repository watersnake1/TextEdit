package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by christian on 12/31/16.
 */
public class DocumentItem {
    private JPanel ItemPanel;
    private JLabel name;
    private JPanel ImagePanel;
    private String title;

    public DocumentItem(String title) {
        this.title = title;
        name.setText(title);
    }

    public JPanel getItemPanel() {return ItemPanel;}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new DocumentItem("test").getItemPanel());
        frame.pack();
        frame.setVisible(true);
        System.out.println(frame.getWidth());
    }
}
