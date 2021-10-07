import javax.swing.*;

public class Menu extends JMenuBar {

    //***Attributes***

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
    private JCheckBoxMenuItem toolBarView;
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






    public Menu(){
        initMenu();
    }

    public void initMenu(){
        menu = new JMenuBar();

        //*** FILE ***
        file = new JMenu();
        file.setText("File");
        //File Options
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


        // *** EDIT ***
        edit = new JMenu();
        edit.setText("Edit");
            //Edit Options
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


        // *** VIEW ***
        view = new JMenu();
        view.setText("View");
        //View Options
        explorerView = new JCheckBoxMenuItem("Explorer Panel");
        view.add(explorerView);
        consoleView = new JCheckBoxMenuItem("Console Panel");
        view.add(consoleView);
        toolBarView = new JCheckBoxMenuItem("ToolBar Panel");
        view.add(toolBarView);
        zenMode = new JCheckBoxMenuItem("Zen Mode");
        view.add(zenMode);
        darkMode = new JCheckBoxMenuItem("Dark Mode");
        view.add(darkMode);

        menu.add(view);

        // *** CODE ***
        code=new JMenu();
        code.setText("Code");
        //Code Options
        run = new JMenuItem("Run");
        code.add(run);
        build= new JMenuItem("Build");
        code.add(build);

        menu.add(code);


        // *** HELP ***
        help = new JMenu();
        help.setText("Help");
        //Help Options
        about = new JMenuItem("About");
        help.add(about);
        seeHelp = new JMenuItem("See Help");
        help.add(seeHelp);

        menu.add(help);






    }

    //***Getters And Setters***

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }

    public JMenu getFile() {
        return file;
    }

    public void setFile(JMenu file) {
        this.file = file;
    }

    public JMenuItem getNewFile() {
        return newFile;
    }

    public void setNewFile(JMenuItem newFile) {
        this.newFile = newFile;
    }
}
