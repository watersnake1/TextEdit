package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by christian on 12/15/16.
 */
public class NewFileView extends JFrame {
    private JPanel panel1;
    private JTree tree1;
    private JTextField egFilenameTxtTextField;
    private JButton saveButton;
    private JButton fooButton;
    private JSplitPane newFileSplitPane;
    private FileTree filePickerTree;

    public NewFileView() {
        //filePickerTree = new FileTree(new File("/home/bouy"), new JFrame(), new JEditorPane());
        setContentPane(panel1);
        setVisible(true);
        newFileSplitPane.setLeftComponent(filePickerTree);
        tree1.setPreferredSize(new Dimension(50,200));
        pack();
        //System.out.println(panel1.getWidth() + "" + panel1.getHeight());
    }
}
