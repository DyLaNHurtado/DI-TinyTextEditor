import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    //Attributes

    //MainPanel
    private JPanel panelView;

    //--- MENU ---

    private JMenuBar menu;

    //File
    private JMenu file;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveAsFile;
    private JMenuItem printFile;
    private JMenuItem exitFile;

    //Edit
    private JMenu edit;
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenuItem delete;

    //View
    private JMenu view;
    private JCheckBoxMenuItem explorerView;
    private JCheckBoxMenuItem consoleView;
    private JCheckBoxMenuItem zenMode;
    private JCheckBoxMenuItem darkMode;


    //Code
    private JMenu code;
    private JMenuItem run;
    private JMenuItem build;

    //Help
    private JMenu help;
    private JMenuItem about;
    private JMenuItem seeHelp;

    //--- TEXTAREA ---
    private TextArea textArea;

    //--- TOOLBAR ---
    private JToolBar toolBar;
    private JButton runToolBar;
    private JButton buildToolBar;
    private JButton explorerToolBar;
    private JButton consoleToolBar;
    private JButton zenModeToolBar;

    //--- CONSOLE ---
    private Console console;

    //--- Explorer ---
    private JTree tree;


    public View(){

        //View
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setIconImage(new ImageIcon(getClass().getResource("icono.png")).getImage());
        setPreferredSize(new Dimension(1200,700));
        setMinimumSize(new Dimension(800,600));
        panelView=new JPanel();
        panelView.setSize(500,500);
        panelView.setLayout(new BorderLayout());
        add(panelView);


        //Menu
        initMenu();

        //TextArea
        textArea=new TextArea();
        panelView.add(textArea,BorderLayout.CENTER);

        //Console
        console=new Console();
        console.setPreferredSize(new Dimension(200,200));
        panelView.add(console,BorderLayout.SOUTH);

        //Explorer
        tree=new JTree();
        tree.setPreferredSize(new Dimension(150,200));
        panelView.add(tree,BorderLayout.WEST);

        //ToolBar
        initToolBar();

        pack();
    }


    private void initMenu(){
        //--- MENU ---
        menu = new JMenuBar();

        //File
        file = new JMenu();
        file.setText("File");

        newFile = new JMenuItem("New");
        file.add(newFile);
        openFile = new JMenuItem("Open");
        file.add(openFile);
        saveFile = new JMenuItem("Save");
        file.add(saveFile);
        saveAsFile = new JMenuItem("Save As");
        file.add(saveAsFile);
        printFile = new JMenuItem("Print");
        file.add(printFile);
        exitFile = new JMenuItem("Exit");
        file.add(exitFile);

        menu.add(file);


        //Edit
        edit = new JMenu();
        edit.setText("Edit");

        undo = new JMenuItem("Undo");
        edit.add(undo);
        redo = new JMenuItem("Redo");
        edit.add(redo);
        copy = new JMenuItem("Copy");
        edit.add(copy);
        cut = new JMenuItem("Cut");
        edit.add(cut);
        delete = new JMenuItem("Delete");
        edit.add(delete);

        menu.add(edit);


        //View
        view = new JMenu();
        view.setText("View");

        explorerView = new JCheckBoxMenuItem("Explorer Panel");
        view.add(explorerView);
        consoleView = new JCheckBoxMenuItem("Console Panel");
        view.add(consoleView);
        zenMode = new JCheckBoxMenuItem("Zen Mode");
        view.add(zenMode);
        darkMode = new JCheckBoxMenuItem("Dark Mode");
        view.add(darkMode);

        menu.add(view);

        // Code
        code=new JMenu();
        code.setText("Code");

        run = new JMenuItem("Run");
        code.add(run);
        build= new JMenuItem("Build");
        code.add(build);

        menu.add(code);


        // Help
        help = new JMenu();
        help.setText("Help");

        about = new JMenuItem("About");
        help.add(about);
        seeHelp = new JMenuItem("See Help");
        help.add(seeHelp);

        menu.add(help);

        setJMenuBar(menu);
    }
    private void initToolBar(){
        toolBar=new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(32,600));
        toolBar.setLayout(new GridLayout(0,1));


        toolBar.addSeparator();
        runToolBar=new JButton("Run");
        toolBar.add(runToolBar);
        toolBar.addSeparator();
        buildToolBar=new JButton("Build");
        toolBar.add(buildToolBar);
        toolBar.addSeparator();
        explorerToolBar=new JButton("Explorer");
        toolBar.add(explorerToolBar);
        toolBar.addSeparator();
        consoleToolBar=new JButton("Console");
        toolBar.add(consoleToolBar);
        toolBar.addSeparator();
        zenModeToolBar=new JButton("Zen Mode");
        toolBar.add(zenModeToolBar);
        toolBar.addSeparator();



        add(toolBar,BorderLayout.WEST);

    }


}
