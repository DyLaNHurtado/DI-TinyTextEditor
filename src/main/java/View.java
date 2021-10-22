import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class View extends JFrame {
    //Attributes
    private JFileChooser selector;
    private File archivoAbierto;

    private Clipboard clip;
    //MainPanel
    private final JPanel panelView;

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
    private final UndoManager deshacer;
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenuItem paste;
    private JMenuItem delete;

    //View
    private JMenu view;
    private JCheckBoxMenuItem explorerView;
    private JCheckBoxMenuItem consoleView;
    private JCheckBoxMenuItem zenMode;


    //Code
    private JMenu code;
    private JMenuItem run;
    private JMenuItem build;

    //Help
    private JMenu help;
    private JMenuItem about;
    private JMenuItem seeHelp;

    //--- TEXTAREA ---
    private final TextArea textArea;

    //--- TOOLBAR ---
    private JToolBar toolBar;
    private JButton runToolBar;
    private JButton buildToolBar;

    //--- CONSOLE ---
    private final Console console;

    //--- Explorer ---
    private final JTree tree;


    public View() {

        //View
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setIconImage(new ImageIcon(getClass().getResource("icono.png")).getImage());
        setPreferredSize(new Dimension(1200, 700));
        setMinimumSize(new Dimension(800, 600));
        setTitle("TinyTextEditor");
        panelView = new JPanel();
        panelView.setSize(500, 500);
        panelView.setLayout(new BorderLayout());
        add(panelView);

        //TextArea
        textArea = new TextArea();
        panelView.add(textArea, BorderLayout.CENTER);

        //Menu
        initMenu();
        deshacer = new UndoManager();
        deshacer.setLimit(50);


        //Console
        console = new Console();
        console.setPreferredSize(new Dimension(200, 200));
        panelView.add(console, BorderLayout.SOUTH);

        //Explorer
        tree = new JTree();
        tree.setBackground(Color.darkGray);
        tree.setForeground(Color.WHITE);
        tree.setPreferredSize(new Dimension(150, 200));
        panelView.add(tree, BorderLayout.WEST);

        //ToolBar
        initToolBar();

        //LookAndFeel
        initLookAndFeel();

        pack();
    }


    private void initMenu() {
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

        //__setFileListeners__
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });


        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

                saveAsFile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveAsFile();
                    }
                });

        printFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printFile();
            }
        });

        exitFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


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
        paste = new JMenuItem("Paste");
        edit.add(paste);
        delete = new JMenuItem("Delete");
        edit.add(delete);

        menu.add(edit);

        textArea.getTextArea().getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                deshacer.addEdit(e.getEdit());
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoText();
            }
        });

        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redoText();
            }
        });



        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = getSelectedText();
                    cut(text);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = getSelectedText();
                    copy(text);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    paste();
                } catch (AWTException | IOException | UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                }
            }
        });



        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delete();
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //View
        view = new JMenu();
        view.setText("View");

        explorerView = new JCheckBoxMenuItem("Explorer Panel", true);
        view.add(explorerView);
        consoleView = new JCheckBoxMenuItem("Console Panel", true);
        view.add(consoleView);
        zenMode = new JCheckBoxMenuItem("Zen Mode", false);
        view.add(zenMode);

        menu.add(view);

        explorerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.setVisible(explorerView.getState());
            }
        });



        consoleView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.setVisible(consoleView.getState());
            }
        });



        zenMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zenMode();
            }
        });


        // Code
        code = new JMenu();
        code.setText("Code");

        run = new JMenuItem("Run");
        code.add(run);
        build = new JMenuItem("Build");
        code.add(build);

        menu.add(code);

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    build();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        // Help
        help = new JMenu();
        help.setText("Help");

        about = new JMenuItem("About");
        help.add(about);
        seeHelp = new JMenuItem("See Help");
        help.add(seeHelp);

        menu.add(help);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });

        seeHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    goToURL();
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setJMenuBar(menu);
    }


    private void initToolBar() {
        toolBar = new JToolBar();
        toolBar.setBackground(Color.DARK_GRAY);
        toolBar.setBorderPainted(true);
        toolBar.setFloatable(false);
        toolBar.setLayout(new GridLayout(1, 0));

        buildToolBar = new JButton("Build");
        buildToolBar.setBackground(new Color(0, 128, 106));
        buildToolBar.setForeground(Color.WHITE);
        toolBar.add(buildToolBar);
        runToolBar = new JButton("Run");
        runToolBar.setBackground(new Color(0, 128, 106));
        runToolBar.setForeground(Color.WHITE);
        toolBar.add(runToolBar);

        add(toolBar, BorderLayout.NORTH);

        runToolBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        buildToolBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    build();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });


    }

    private void newFile() {
        //Pregunta si quiere guardar antes si hay algo escrito
        if (!textArea.getText().equals("")) {
            if (JOptionPane.showConfirmDialog(newFile, "Do you want to save the actual file? ", "Alert Dialog",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                try {
                    saveFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            textArea.setText("");
        }
    }

    private void openFile() throws IOException {
        //Pregunta si quiere guardar antes si hay algo escrito
        if (!textArea.getText().equals("")) {
            if (JOptionPane.showConfirmDialog(openFile, "Do you want to save the actual file? ", "Alert Dialog",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                try {
                    saveFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        //Abrir el archivo y escribirlo en el textArea
        selector = new JFileChooser();
        selector.setCurrentDirectory(new File(System.getProperty("user.home")));
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selector.setAcceptAllFileFilterUsed(false);
        int option = selector.showOpenDialog(this);
        archivoAbierto = selector.getSelectedFile();
        setTitle("TinyTextEditor - " + archivoAbierto.getName());
        if (option == JFileChooser.APPROVE_OPTION) {
            FileReader fr = new FileReader(archivoAbierto);
            BufferedReader br = new BufferedReader(fr);
            textArea.setText("");
            String line = br.readLine();
            while (!(line == null)) {
                textArea.getTextArea().append(line + "\n");
                line = br.readLine();
            }
            br.close();
        }
    }


    private void saveFile() throws IOException {

        if (archivoAbierto == null) {
            archivoAbierto = saveAsFile();

        } else {
            try (FileWriter writer = new FileWriter(archivoAbierto)) {
                writer.write(textArea.getText());
                setTitle("TinyTextEditor - " + archivoAbierto.getName());
                JOptionPane.showMessageDialog(null, "File has been saved correctly", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private File saveAsFile() {
        selector = new JFileChooser();
        selector.setCurrentDirectory(new File(System.getProperty("user.home")));
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selector.setAcceptAllFileFilterUsed(false);
        int option = selector.showSaveDialog(this);
        File fileNotSaved = selector.getSelectedFile();
        try (FileWriter writer = new FileWriter(fileNotSaved)) {
            if (option == JFileChooser.APPROVE_OPTION) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(null, "File has been saved correctly", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivoAbierto = fileNotSaved;
        setTitle("TinyTextEditor - " + archivoAbierto.getName());
        return fileNotSaved;
    }

    private void printFile() {
        textArea.print();
    }

    private void undoText() {
        if (deshacer.canUndo())
            deshacer.undo();
    }

    private void redoText() {
        if (deshacer.canRedo())
            deshacer.redo();
    }

    private void copy(String text) {
        StringSelection ss = new StringSelection(text);
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(ss, null);

    }

    private void paste() throws AWTException, IOException, UnsupportedFlavorException {
        String result = "";
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clip.getContents(null);
        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            result = (String) content.getTransferData(DataFlavor.stringFlavor);
        }
        int pos = textArea.getTextArea().getCaretPosition();
        textArea.getTextArea().insert(result, pos);
    }

    private String getSelectedText() {
        return textArea.getTextArea().getSelectedText();
    }

    private void cut(String text) throws AWTException {
        StringSelection ss = new StringSelection(text);
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(ss, null);
        delete();
    }

    private void delete() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);

    }
    private void zenMode() {
        if (zenMode.getState()) {
            explorerView.setState(false);
            tree.setVisible(false);
            consoleView.setState(false);
            console.setVisible(false);
            toolBar.setVisible(false);
        } else {
            explorerView.setState(true);
            tree.setVisible(true);
            consoleView.setState(true);
            console.setVisible(true);
            toolBar.setVisible(true);
        }
    }

    private void run() throws IOException {

        if (Objects.equals(textArea.getText(), "") || archivoAbierto == null) {
            JOptionPane.showMessageDialog(null, "Select a valid file or save it to run it ", "Alert Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saveFile();
            Runtime cmd = Runtime.getRuntime();
            String runJava = "java " + archivoAbierto.getPath();
            readInConsole(cmd, runJava);
            JOptionPane.showMessageDialog(null, "Run done!", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void build() throws IOException {
        if (Objects.equals(textArea.getText(), "") || archivoAbierto == null) {
            JOptionPane.showMessageDialog(null, "Select a valid file or save it to build it ", "Alert Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            saveFile();
            Runtime cmd = Runtime.getRuntime();
            String buildJava = "javac " + archivoAbierto.getPath();
            readInConsole(cmd, buildJava);
            JOptionPane.showMessageDialog(null, "Build done!", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void readInConsole(Runtime cmd, String command) throws IOException {

        Process proc = cmd.exec(command);
        InputStream inputStream = proc.getInputStream();
        InputStream errorStream = proc.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
        BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
        BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader);
        String inputline = "";
        String errorline = "";
        console.setText("");
        console.getConsole().setForeground(Color.WHITE);
        while ((inputline = inputBufferedReader.readLine()) != null) {
            console.getConsole().append(inputline + "\n");
        }
        while ((errorline = errorBufferedReader.readLine()) != null) {
            console.getConsole().append(errorline + "\n");
            console.getConsole().setForeground(Color.RED);
        }
    }


    private void about() {
        JOptionPane.showMessageDialog(about,
                "\t\tTinyTextEditor\n" +
                        "\n" +
                        "RuntimeVersion: 0.1 \n" +
                        "Author: Dylan Hurtado Lopez\n" +
                        "Powered By Java Swing\n",
                "About",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void goToURL() throws URISyntaxException, IOException {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                java.net.URI uri = new java.net.URI("https://github.com/DyLaNHurtado/TinyTextEditor");
                desktop.browse(uri);
            }
        }
    }

    private void initLookAndFeel() {
        System.setProperty("apple.awt.application.appearance", "system");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

}
