/**
 * Created by christian on 12/16/16.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

    /**
     * Display a file system in a JTree view
     *
     * Need a way to get file associated with the node
     * start at top level and keep concat strings until a full file path is made,
     * then create a file from that url
     */
    public class FileTree extends JPanel {
        /** Construct a FileTree */
        private JFrame frame;
        private JEditorPane editorPane;

        public FileTree(File dir, JFrame frame, JEditorPane editorPane) {
            setLayout(new BorderLayout());
            this.frame = frame;
            this.editorPane = editorPane;
            // Make a tree list with all the nodes, and make it a JTree
            JTree tree = new JTree(addNodes(null, dir));

            // Add a listener
            tree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    //get the selected node
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                            .getPath().getLastPathComponent();
                    //if the node is a leaf, keep going, else do nothing
                    if (node.isLeaf()) {
                        //get a string consisting of the leafs file path
                        String path = node.getParent().toString() + "/" + node.toString();
                        System.out.println("You selected " + node.getParent().toString() + "/" + node.toString());
                        //construct a file from this string
                        File target = new File(path);
                        //set the title of the frame to the name of the file
                        frame.setTitle(target.getName());
                        //read from the file
                        FileReader in = null;
                        try {
                            in = new FileReader(target);
                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(frame, "Unsupported Filetype");
                            e1.printStackTrace();
                        }
                        char[] buffer = new char[100000000];
                        int n = 0;
                        try {
                            n = in.read(buffer);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        String text = new String(buffer, 0, n);
                        editorPane.setText(text);
                        try {
                            in.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            });

            // Lastly, put the JTree into a JScrollPane.
            JScrollPane scrollpane = new JScrollPane();
            scrollpane.getViewport().add(tree);
            add(BorderLayout.CENTER, scrollpane);
        }

        /** Add nodes from under "dir" into curTop. Highly recursive. */
        DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
            String curPath = dir.getPath();
            DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
            if (curTop != null) { // should only be null at root
                curTop.add(curDir);
            }
            Vector ol = new Vector();
            String[] tmp = dir.list();
            for (int i = 0; i < tmp.length; i++)
                ol.addElement(tmp[i]);
            Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
            File f;
            Vector files = new Vector();
            // Make two passes, one for Dirs and one for Files. This is #1.
            for (int i = 0; i < ol.size(); i++) {
                String thisObject = (String) ol.elementAt(i);
                String newPath;
                if (curPath.equals("."))
                    newPath = thisObject;
                else
                    newPath = curPath + File.separator + thisObject;
                if ((f = new File(newPath)).isDirectory())
                    addNodes(curDir, f);
                else
                    files.addElement(thisObject);
            }
            // Pass two: for files.
            for (int fnum = 0; fnum < files.size(); fnum++)
                curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
            return curDir;
        }

        public Dimension getMinimumSize() {
            return new Dimension(200, 400);
        }

        public Dimension getPreferredSize() {
            return new Dimension(200, 400);
        }
    }

