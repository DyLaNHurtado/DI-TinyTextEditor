import javax.swing.*;
import java.awt.*;

public class ToolBar {
    //Attributes
    private JPanel toolBar;
    private JButton run;
    private JButton build;
    private JButton explorer;
    private JButton console;
    private JButton zenMode;


    public ToolBar(){
        initToolbar();
    }
    public void initToolbar(){

        toolBar=new JPanel();
        toolBar.setPreferredSize(new Dimension(60,800));
        toolBar.setLayout(new GridLayout(0,1));

        //Icons
        run=new JButton("Run");
        run.setPreferredSize(new Dimension(10,10));
        toolBar.add(run);

        build=new JButton("Build");
        toolBar.add(build);

        explorer=new JButton("Explorer");
        toolBar.add(explorer);

        console=new JButton("Console");
        toolBar.add(console);

        zenMode=new JButton("Zen Mode");
        toolBar.add(zenMode);



    }

    //Getters And Setters


    public JPanel getToolBar() {
        return toolBar;
    }

    public void setToolBar(JPanel toolBar) {
        this.toolBar = toolBar;
    }
}
