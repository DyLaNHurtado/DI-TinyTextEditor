import javax.swing.*;
import java.awt.*;

public class Text {

    //Attributes
    private JPanel panel;
    private JTextArea text;
    private JScrollPane scroll;
    Text(){
        initText();
    }
    public void initText(){
        panel=new JPanel();
        text=new JTextArea();
        scroll = new JScrollPane(text);
        panel.setLayout(new BorderLayout());
        panel.add(scroll, BorderLayout.EAST);

        panel.add(text);

    }

    //Getters And Setters

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextArea getText() {
        return text;
    }

    public void setText(JTextArea text) {
        this.text = text;
    }
}
