package GraphicalInterface;

import ActionListeners.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by christian on 10/19/16.
 * User interface view controller - creates all the components for the main view
 */
public class UserInterface extends JFrame {
    //first group of objects are created by .form file
    //the main editor pane
    private JEditorPane editorPane1;
    //panel contains all toolbars and editor pane
    private JPanel panel1;
    //top level j panel
    private JPanel upperJPanel;
    //place holder file tree
    private JTree tree1;
    //split pane
    private JSplitPane splitPane;

    //tool bar buttons
    private JButton saveButton;
    private JButton openButton;
    private JButton clearButton;
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton aboutButton;
    private JButton quitButton;

    //tool bar with save button
    private JToolBar topToolBar;
    //font selection combo box
    private JComboBox fontPicker;
    //html and font tool bar
    private JToolBar bottomToolBar;
    //size picker combo box
    private JComboBox fontSizePicker;
    //toggle html mode radio button
    private JRadioButton shouldBeHtml;
    //toggle bold radio button
    private JRadioButton boldButton;
    //toggle italics radio button
    private JRadioButton italicsButton;
    //reset to no formatting radio button
    private JButton plainButton;
    //create a new file button
    private JButton newButton;

    //command bar items
    //tool bar that is for running a command
    private JToolBar runCommandToolBar;
    //label that says run command
    private JLabel runLabel;
    //text field to accept system commands
    private JTextField commandTextField;
    //button that executes system commands
    private JButton enterButton;

    //button to auto compile code
    private JLabel compileButton;
    //button to auto run code
    private JLabel runButton;
    //the tool bar with the run button
    private JToolBar bottomMostToolBar;
    //lang support combo box
    private JComboBox languageComboBox;
    private JButton addTab;
    private JButton copyToDropbox;
    private JButton filesButton;
    private JTabbedPane topTabbedPane;


    //top level imbedded menu
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
    private JMenuItem testItem;

    //path to the file
    private String filePath;
    //target file to work with
    private File target;
    //determine if should match curly braces
    private boolean shouldBeCodeEditor;

    //icons
    private ImageIcon compileIcon;
    private ImageIcon runIcon;
    private ImageIcon saveIcon;


    private LangTypeComboItem java;
    private LangTypeComboItem python;
    private String currentLang;

    private Document pane;

    private FileTree leftFileTree;

    private TabbedPopUpMenu popUpMenu;

    /**
     * Create a new instance of the editor with a blank unsaved file
     */
    public UserInterface() {
        //set the title of the main window
        super("Sweat editor - Untitled Text");
        //make the swing components look like the system (will match with linux themeing)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        //init menu bar items, set the menu bar ////////////////////////////////////////////////
        topMenu = new JMenuBar();
        file = new JMenu("File");
        about = new JMenuItem("About");
        quit = new JMenuItem("Quit");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");
        newDoc = new JMenuItem("New");
        testItem = new JMenuItem("test");

        edit = new JMenu("Edit");
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");

        //set up the tool bar menus
        saveIcon = new ImageIcon("png/folder.png");
        //saveButton.setIcon(saveIcon);

        //add in jmenus
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
        edit.add(testItem);

        //code for top menu bar is hard coded
        setJMenuBar(topMenu);
        ////////////////////////////////////////////////////////////////////////////

        leftFileTree = new FileTree(new File(".."), this, editorPane1, topTabbedPane);

        //in future, if this is true editor will match curly braces
        shouldBeCodeEditor = true;

        //configure the bottom menu items and set thier icons
        currentLang = "java";
        java = new LangTypeComboItem(false, "java");
        python = new LangTypeComboItem(true, "python");
        compileIcon = new ImageIcon("reload.png");
        runIcon = new ImageIcon("play-button.png");
        languageComboBox.addItem(java);
        languageComboBox.addItem(python);


        //set the size
        setPreferredSize(new Dimension(900,700));


        //set the left side to be a file tree in current working dir
        splitPane.setLeftComponent(leftFileTree);
        add(upperJPanel);
        topMenu.add(file);
        //set the focus of the window to be the top most jpanel
        setContentPane(upperJPanel);
        pack();
        //close on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //get the document of the editor pane (for formatting)
        pane = editorPane1.getDocument();

        //this is the default font
        editorPane1.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        //prevent tool bars from floating
        topToolBar.setFloatable(false);
        bottomToolBar.setFloatable(false);
        //create the font pickers
        configureFontPicker(fontPicker, fontSizePicker);
        //set the tab size to 2
        if(pane instanceof Document) {
            pane.putProperty(PlainDocument.tabSizeAttribute, 2);
        }
        filePath = null;
        popUpMenu = new TabbedPopUpMenu(topTabbedPane);
        //start action listeners
        actionListeners();
        playground();
        //matchCurlyBraces();
        //repaint the screen just in case
        panel1.updateUI();
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
        save.addActionListener(new SaveActionListener(editorPane1, panel1, upperJPanel, topTabbedPane, leftFileTree));
        open.addActionListener(new OpenActionListener(editorPane1, panel1, target, this, topTabbedPane));
        copy.addActionListener(new CopyActionListener(editorPane1));
        paste.addActionListener(new PasteActionListener(editorPane1));
        cut.addActionListener(new CutActionListener(editorPane1));
        clearButton.addActionListener(new ClearActionListener(editorPane1));
        //newDoc.addActionListener(new NewActionListener());

        saveButton.addActionListener(new SaveActionListener(editorPane1, panel1, upperJPanel, topTabbedPane, leftFileTree));
        openButton.addActionListener(new OpenActionListener(editorPane1, panel1, target, this, topTabbedPane));
        cutButton.addActionListener(new CutActionListener(editorPane1));
        copyButton.addActionListener(new CopyActionListener(editorPane1));
        pasteButton.addActionListener(new PasteActionListener(editorPane1));
        aboutButton.addActionListener(new AboutActionListener(panel1));
        quitButton.addActionListener(new QuitActionListener());
        fontPicker.addActionListener(new FontSetterActionListener(fontPicker, editorPane1, boldButton, italicsButton));
        shouldBeHtml.addActionListener(new LineWrapActionListener(editorPane1, shouldBeHtml));
        boldButton.addActionListener(new RadioButtonActionListener(editorPane1, boldButton));
        italicsButton.addActionListener(new RadioButtonActionListener(editorPane1, italicsButton));
        plainButton.addActionListener(new PlainButtonActionListener(editorPane1));
        enterButton.addActionListener(new EnterButtonActionListener(commandTextField));
        compileButton.addMouseListener(new BottomToolBarActionListener(0, this));
        runButton.addMouseListener(new BottomToolBarActionListener(1, this));
        languageComboBox.addActionListener(new LanguageActionListener(this, languageComboBox));
        addTab.addActionListener(new AddTabActionListener(topTabbedPane));
        topTabbedPane.addMouseListener(new RightClickActionListener(popUpMenu, topTabbedPane));
        //pane.addDocumentListener(new ActionListeners.TextInputListener(editorPane1, this));
    }

    /**
     * Match a curly brace or bracket automatically
     * (not yet working)
     */
    public void matchCurlyBraces() {
        System.out.println(editorPane1.getDocument());
        try {
            editorPane1.getDocument().insertString(0, "}", new SimpleAttributeSet());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
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
        testItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftFileTree.changeDir(new File(""));
            }
        });

    }

    /**
     * set the current language type of the editor
     * @param currentLang
     */
    public void setCurrentLang(String currentLang) {
        this.currentLang = currentLang;
        System.out.println("the current lang is " + this.currentLang);
    }

    //getters and setters
    public String getText() { return editorPane1.getText();}
    public JEditorPane getEditorPane1() {return editorPane1;}
    public JPanel getPanel1() {return panel1;}
    public JMenuBar getTopMenu() {return topMenu;}
    public JMenu getFile() {return file;}
    public String getCurrentLang() {return currentLang;}
    public File getTarget() {return target;}
}
