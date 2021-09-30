import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    int count = 0;
    JLabel label;
    JFrame frame;
    JButton button;
    JPanel panel;

    public GUI() {

        frame = new JFrame();
        button = new JButton("START");
        button.addActionListener(this);

        label = new JLabel("Number of clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);
        panel.setBackground(Color.black);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SPACE GAME");
        frame.pack();
        frame.setVisible(true);
    }

    public void startGUI() {
        GUI gui = new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        Adventure.start();
    }
}