import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    //Attributes
    private JPanel panelView;
    private Menu menu;
    private Text text;
    private ToolBar toolBar;


    public View(){

        menu = new Menu();
        text = new Text();
        toolBar = new ToolBar();
        initComponents();
    }

    private void initComponents(){
        //View
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setIconImage(new ImageIcon(getClass().getResource("icono.png")).getImage());
        setPreferredSize(new Dimension(1200,700));
        setMinimumSize(new Dimension(800,600));
        panelView=new JPanel();
        add(panelView);
        panelView.setSize(500,500);
        panelView.setLayout(new BorderLayout());

        //Menu
        menu.initMenu();
        setJMenuBar(menu.getMenu());

        //Text
        text.initText();
        panelView.add(text.getPanel(),BorderLayout.CENTER);

        //ToolBar
        toolBar.initToolbar();
        panelView.add(toolBar.getToolBar(),BorderLayout.WEST);

        pack();
    }
}
