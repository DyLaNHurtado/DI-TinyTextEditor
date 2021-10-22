import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.print.PrinterException;

public class TextArea extends JScrollPane {
    private JTextArea textArea;
    private JTextArea lines;

    public TextArea (){

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("");
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Console", Font.PLAIN, 14));
        textArea.setBorder(new EmptyBorder(0,10,0,10));
        textArea.setTabSize(2);
        this.setViewportView(textArea);

        lines= new JTextArea("1"+"        ");
        lines.setBorder(textArea.getBorder());
        lines.setFont(textArea.getFont());
        lines.setBackground(new Color(0,128,106));
        lines.setForeground(Color.white);
        lines.setEditable(false);
        lines.setFocusable(false);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = textArea.getDocument().getLength();
                Element root = textArea.getDocument().getDefaultRootElement();
                String text = "1"+"        " + System.getProperty("line.separator");
                for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i+"        " + System.getProperty("line.separator");
                }
                return text;
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
        });
        setRowHeaderView(lines);

    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setText(String text){
        textArea.setText(text);
    }

    public String getText(){
        return textArea.getText();
    }

    public void print(){
        try {
            textArea.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
