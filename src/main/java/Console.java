import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.print.PrinterException;

public class Console extends JScrollPane {
    private JTextArea console;

    public Console (){

        console = new JTextArea();
        console.setEditable(false);
        console.setBackground(Color.DARK_GRAY);
        console.setForeground(Color.WHITE);
        console.setBorder(new EmptyBorder(10,20,10,10));
        this.setViewportView(console);
    }

    public JTextArea getConsole() {
        return console;
    }

    public void setText(String text){
        console.setText(text);
    }

    public String getText(){
        return console.getText();
    }

    public void print(){
        try {
            console.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

}
