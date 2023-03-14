import java.awt.*;
import javax.swing.*;

public class Main {
    static public long c;
    static public long mv;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Prime Num Calculator");
        frame.getContentPane().setBackground(new java.awt.Color(128,128,128));
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));

        final JTextField text1 = new JTextField();
        text1.setBounds(30, 45, 150, 24);
        JLabel ul1 = new JLabel("N");
        ul1.setBounds(240, 40, 150, 30);
        ul1.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 23));
        ul1.setForeground(new java.awt.Color(0,0,0));

        final JTextField text2 = new JTextField();
        text2.setBounds(30, 95, 150, 24);
        JLabel ul2 = new JLabel("Buffer Size ");
        ul2.setBounds(240, 90, 150, 30);
        ul2.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 23));
        ul2.setForeground(new java.awt.Color(0,0,0));

        final JTextField text3 = new JTextField();
        text3.setBounds(30, 145, 150, 24);
        JLabel ul3 = new JLabel("Output File ");
        ul3.setBounds(240, 140, 150, 30);
        ul3.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 23));
        ul3.setForeground(new java.awt.Color(0,0,0));
        JButton button = new JButton("Start Producer");
        button.setBounds(30, 200, 300, 40);
        button.setFont(new Font(Font.SERIF, Font.BOLD, 22));

        JLabel al1 = new JLabel("The Largest Prime num: ");
        al1.setBounds(30, 260, 310, 30);
        al1.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 23));
        JLabel answer1 = new JLabel("!");
        answer1.setBounds(310, 260, 60, 30);
        answer1.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        answer1.setForeground(Color.WHITE);

        JLabel al2 = new JLabel("Num of elem Prime Count: ");
        al2.setBounds(30, 300, 300, 30);
        al2.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 23));
        JLabel answer2 = new JLabel("!");
        answer2.setBounds(310, 300, 150, 30);
        answer2.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        answer2.setForeground(Color.WHITE);

        JLabel al3 = new JLabel("Time elapsed since start: ");
        al3.setBounds(30, 340, 300, 30);
        al3.setFont(new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 22));
        JLabel answer3 = new JLabel("!");
        answer3.setBounds(310, 340, 60, 30);
        answer3.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        answer3.setForeground(Color.WHITE);

        // Show Frame Elements
        frame.setSize(400, 450);
        frame.add(ul1);
        frame.add(ul2);
        frame.add(ul3);
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(button);
        frame.add(al1);
        frame.add(al2);
        frame.add(al3);
        frame.add(answer1);
        frame.add(answer2);
        frame.add(answer3);
        frame.setLayout(null);
        frame.setVisible(true);

        button.addActionListener(e -> {
            c = mv = 0;
            long startTime = System.currentTimeMillis();
            Buffer buf = new Buffer(Integer.parseInt(text2.getText()));

            Thread producer = new Thread(new Producer(buf, Integer.parseInt(text1.getText())));
            Thread consumer = new Thread(new Consumer(buf, text3.getText()));
            producer.start();
            consumer.start();
            try {
                
                consumer.join();
                producer.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        
        answer1.setText(String.valueOf(mv));
        answer2.setText(String.valueOf(c));
        answer3.setText(System.currentTimeMillis() - startTime + " ms");
        SwingUtilities.updateComponentTreeUI(frame);
    });
}
}