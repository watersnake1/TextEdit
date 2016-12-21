import oracle.jrockit.jfr.JFR;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by christian on 10/19/16.
 * User interface view controller - creates all the components for the main view
 */
public class UserInterface extends JFrame {
    private JFrame rootFrame;
    private JEditorPane editorPane1;
    private JPanel panel1;
    private JPanel upperJPanel;
    private JTree tree1;
    private JSplitPane splitPane;
    private JButton saveButton;
    private JButton openButton;
    private JButton clearButton;
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton aboutButton;
    private JButton quitButton;
    private JToolBar topToolBar;
    private JComboBox fontPicker;
    private JToolBar bottomToolBar;
    private JComboBox fontSizePicker;
    private JRadioButton shouldBeHtml;
    private JRadioButton boldButton;
    private JRadioButton italicsButton;
    private JButton plainButton;
    private JButton newButton;
    private JToolBar runCommandToolBar;
    private JLabel runLabel;
    private JTextField commandTextField;
    private JButton enterButton;
    private JMenuBar topMenu;
    private JMenu file;
    private JMenu edit;
    private JMenuItem about;
    private JMenuItem quit;
    private JMenuItem save;
    private JMenuItem open;
    private JMenuItem cut;
    private JMenuItem paste;
    private JMenuItem copy;
    private JMenuItem newDoc;
    private String filePath;
    private File target;
    private boolean shouldBeCodeEditor;
    private int[] fontSizes;

    /**
     * Create a new instance of the editor with a blank unsaved file
     */
    public UserInterface() {
        super("Sweat editor - Untitled Text");
        //make the swing components look like the system (will match with linux themeing)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        topMenu = new JMenuBar();
        file = new JMenu("File");
        about = new JMenuItem("About");
        quit = new JMenuItem("Quit");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");
        newDoc = new JMenuItem("New");

        edit = new JMenu("Edit");
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");

        //in future, if this is true editor will match curly braces
        shouldBeCodeEditor = true;

        //code for top menu bar is hard coded
        setJMenuBar(topMenu);
        setPreferredSize(new Dimension(900,700));
        topMenu.add(file,0);
        topMenu.add(edit,1);
        file.add(about);
        file.add(save);
        file.add(quit);
        file.add(open);
        file.add(newDoc);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        //set the left side to be a file tree in current working dir
        splitPane.setLeftComponent(new FileTree(new File("."), this, editorPane1));
        add(upperJPanel);
        topMenu.add(file);
        setContentPane(upperJPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Document pane = editorPane1.getDocument();

        editorPane1.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        //prevent tool bars from floating
        topToolBar.setFloatable(false);
        bottomToolBar.setFloatable(false);
        fontSizes = new int[]{8,10,12,14,16,18,24,26};
        //create the font pickers
        configureFontPicker(fontPicker, fontSizePicker);
        //set the tab size to 2
        if(pane instanceof Document) {
            pane.putProperty(PlainDocument.tabSizeAttribute, 2);
        }
        filePath = null;
        actionListeners();
        playground();
        matchCurlyBraces();
    }

    /**
     * set up the combo boxes with all the fonts available on the system
     * @param comboBox the combo box for system font names
     * @param sizeComboBox the combo box for system font sizes
     */
    public void configureFontPicker(JComboBox comboBox, JComboBox sizeComboBox) {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = environment.getAllFonts();
        for (Font f: allFonts) {
            comboBox.addItem(new FontComboItem(f.getName(), 14));
        }
        for (int size: fontSizes) {
            sizeComboBox.addItem(new FontSizeComboItem(size));
        }
    }

    /**
     * prepare and read from a file <code>target</code> at launch
     * @param target the file to read
     */
    public void openWithFile(File target) {
        this.target = target;
        try {
            FileReader openingReader = new FileReader(target);
            char[] buffer = new char[1024];
            String text = new String(buffer, 0, 0);
            editorPane1.setText(text);
            openingReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * create a listener for every component that needs one
     *
     * */
    public void actionListeners() {
        about.addActionListener(new AboutActionListener(panel1));
        quit.addActionListener(new QuitActionListener());
        save.addActionListener(new SaveActionListener(editorPane1, panel1, upperJPanel));
        open.addActionListener(new OpenActionListener(editorPane1, panel1, target, this));
        copy.addActionListener(new CopyActionListener(editorPane1));
        paste.addActionListener(new PasteActionListener(editorPane1));
        cut.addActionListener(new CutActionListener(editorPane1));
        clearButton.addActionListener(new ClearActionListener(editorPane1));
        newDoc.addActionListener(new NewActionListener());

        saveButton.addActionListener(new SaveActionListener(editorPane1, panel1, upperJPanel));
        openButton.addActionListener(new OpenActionListener(editorPane1, panel1, target, this));
        cutButton.addActionListener(new CutActionListener(editorPane1));
        copyButton.addActionListener(new CopyActionListener(editorPane1));
        pasteButton.addActionListener(new PasteActionListener(editorPane1));
        aboutButton.addActionListener(new AboutActionListener(panel1));
        quitButton.addActionListener(new QuitActionListener());
        fontPicker.addActionListener(new FontSetterActionListener(fontPicker, editorPane1, boldButton, italicsButton));
        fontSizePicker.addActionListener(new FontSizeSetterActionListener(editorPane1, fontPicker, fontSizePicker));
        shouldBeHtml.addActionListener(new LineWrapActionListener(editorPane1, shouldBeHtml));
        boldButton.addActionListener(new RadioButtonActionListener(editorPane1, boldButton));
        italicsButton.addActionListener(new RadioButtonActionListener(editorPane1, italicsButton));
        plainButton.addActionListener(new PlainButtonActionListener(editorPane1));
        enterButton.addActionListener(new EnterButtonActionListener(commandTextField));
    }

    /**
     * Match a curly brace or bracket automatically
     * (not yet working)
     */
    public void matchCurlyBraces() {
        if (shouldBeCodeEditor) {
            System.out.println("hit");
            if (editorPane1.getText().length() > 1) {
                if (getLastCharInSequence(editorPane1.getText()) == '{') {
                    editorPane1.setText(editorPane1.getText() + '}');
                } else if (getLastCharInSequence(editorPane1.getText()) == '[') {
                    editorPane1.setText(editorPane1.getText() + ']');
                }
            }
            else {
                return;
            }
        }
    }

    /**
     * get the last char in a sequence of characters
     * @param str
     * @return
     */
    public char getLastCharInSequence(String str) {
        return str.charAt(str.length()-1);
    }

    /**
     * testing area
     */
    public void playground() {
        //CommandOutputView c = new CommandOutputView();

    }

    //getters and setters
    public String getText() { return editorPane1.getText();}
    public JFrame getRootFrame() {return rootFrame;}
    public JEditorPane getEditorPane1() {return editorPane1;}
    public JPanel getPanel1() {return panel1;}
    public JMenuBar getTopMenu() {return topMenu;}
    public JMenu getFile() {return file;}

}
