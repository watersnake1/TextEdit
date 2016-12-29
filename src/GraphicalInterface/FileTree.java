package GraphicalInterface; /**
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
        private JTabbedPane tabbedPane;

        public FileTree(File dir, JFrame frame, JEditorPane editorPane, JTabbedPane tabbedPane) {
            setLayout(new BorderLayout());
            this.frame = frame;
            this.editorPane = editorPane;
            this.tabbedPane = tabbedPane;
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
                        //tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), target.getName());
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
                        JEditorPane edit = new JEditorPane();
                        JScrollPane scrollPane = new JScrollPane(edit);
                        if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("untitled")) {
                            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), target.getName());
                            editorPane.setText(text);
                        }
                        else {
                            tabbedPane.addTab(target.getName(), scrollPane);
                            edit.setText(text);
                        }
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
        public DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
            //get the path to the current file
            String curPath = dir.getPath();
            //create a new tree node at the current top level path
            DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
            //if not at the top, add another node
            if (curTop != null) { // should only be null at root
                curTop.add(curDir);
            }
            Vector ol = new Vector();
            //list out all the files and directories as strings underneath the current file
            String[] tmp = dir.list();
            for (int i = 0; i < tmp.length; i++)
                ol.addElement(tmp[i]);
            //sort the files and directories in alphabetical order
            Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
            File f;
            Vector files = new Vector();
            // Make two passes, one for Dirs and one for Files. This is #1.
            for (int i = 0; i < ol.size(); i++) {
                //get an object as a string out of the vector
                String thisObject = (String) ol.elementAt(i);
                String newPath;
                //if the path is the top level path set new path to the current path and stop
                if (curPath.equals("."))
                    newPath = thisObject;
                //otherwise, set the current path to the top level path plus a slash plus the path to the object element
                else
                    //newPath = curPath + File.separator + thisObject + "/";
                    newPath = new String(curPath + File.separator + thisObject + "/");
                    //System.out.println(newPath + File.separator);
                //if the file is a directory, start again and go into that directory
                if ((f = new File(newPath)).isDirectory() /*(f = new File(newPath).getAbsoluteFile()).isDirectory()*/) {
                    addNodes(curDir, f);
                }
                else
                    files.addElement(thisObject);
            }
            // Pass two: for files.
            for (int fnum = 0; fnum < files.size(); fnum++)
                curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
            return curDir;
        }

        public void changeDir(File dir) {
            addNodes(null, dir);
            updateUI();
        }

        public Dimension getMinimumSize() {
            return new Dimension(200, 400);
        }

        public Dimension getPreferredSize() {
            return new Dimension(200, 400);
        }
    }

