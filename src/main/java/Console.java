import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

public class Console extends JScrollPane {
    private JTextArea console;

    public Console (){

        console = new JTextArea();
        console.setEditable(false);
        this.setViewportView(console);
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
