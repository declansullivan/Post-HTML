import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.io.PrintWriter;

public class PostGenerator extends JFrame implements ActionListener{;

    private int numFields = 4;
    private String imgLink = "";
    private String imgLink2 = "";
    private String title = "";
    private String content = "";

    private JTextField imgField = new JTextField();
    private JTextField hoverImgField2 = new JTextField();
    private JTextField titleField = new JTextField();
    private JTextField contentField = new JTextField();

    private JLabel JimgLink = new JLabel("Primary Image Link", SwingConstants.CENTER);
    private JLabel JimgLink2 = new JLabel("Secondary Image Link", SwingConstants.CENTER);
    private JLabel Jtitle = new JLabel("Post Title", SwingConstants.CENTER);
    private JLabel Jcontent = new JLabel("Post Description", SwingConstants.CENTER);

    private JButton complete = new JButton("Build HTML");

    public static void main(String args[]) {
        PostGenerator window = new PostGenerator("Post Generator");
    }

    public PostGenerator(String s) {
        super(s);
        setLayout(new GridLayout((numFields * 2) + 1, 1));
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        complete.addActionListener(this);

        add(JimgLink);
        add(imgField);

        add(JimgLink2);
        add(hoverImgField2);

        add(Jtitle);
        add(titleField);

        add(Jcontent);
        add(contentField);

        add(complete);
        
        setVisible(true);
    }

    public void writeFile(String link1, String link2, 
                            String title, String content) throws IOException {
        PrintWriter output = new PrintWriter("new_webpost.txt", "UTF-8");

        output.println("Insert into <div class=\"me\"> to add post.\n");

        output.println("<div class=\"moment\">");
         output.format("  <img src=\"%s\"></img>\n", link1);
         output.format("  <img src=\"%s\"></img>\n", link2);
        output.println("    <div class=\"content\">");
         output.format("      <h2>%s</h2>\n", title);
         output.format("      <p>%s</p>\n", content);
        output.println("    </div>");
        output.println("</div>\n");
        output.println("<hr>");
        output.close();

        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        imgLink = imgField.getText();
        imgLink2 = hoverImgField2.getText();
        title = titleField.getText();
        content = contentField.getText();

        try {
            writeFile(imgLink, imgLink2, title, content);
        }
        catch (IOException error) {
            System.out.println("Failed.");
            System.exit(0);
        }
    }
}