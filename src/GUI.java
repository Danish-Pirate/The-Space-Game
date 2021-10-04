import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {
    String input;
    JLabel message;
    public GUI() {
        JFrame frame = new JFrame("SPACE GAME");

        ImageIcon image = new ImageIcon("rocket.png");

        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.getContentPane();


        JPanel panel = new JPanel();
        JTextField commandLine = new JTextField(30);
        JLabel message = new JLabel();

        frame.add(panel);

        panel.setBackground(Color.black);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(1000, 1000 ,1000 ,1000));

        commandLine.setBackground(Color.WHITE);
        commandLine.setBounds(5, 730, 300, 30);
        commandLine.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        commandLine.setFont(new Font("Zig", Font.PLAIN, 18));
        commandLine.setForeground(Color.GREEN);
        commandLine.setBackground(Color.BLACK);
        commandLine.setCaretColor(Color.GREEN);
        commandLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = commandLine.getText();
            }
        });
        commandLine.setVisible(false);


        message.setForeground(Color.GREEN);
        message.setText("Welcome to SPACE GAME! Press any key to start.");
        message.setBounds(5,665, 1000, 100);
        message.setFont(new Font("Courier", Font.PLAIN, 18));


        panel.add(message);
        panel.add(commandLine);


        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                commandLine.setVisible(true);
                commandLine.requestFocusInWindow();
                message.setText("Type \"help\" for help");
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public JLabel getMessage(JLabel message) {
        return message;
    }
}