package projeto;

import javax.swing.*;
import java.awt.*;

public class JoptionpaneFalso extends JDialog {

    public JoptionpaneFalso(JFrame parent, String title, String message) {
        super(parent, title, true);
        
        // Set background colors
        JPanel panel = new JPanel();
        panel.setBackground(new Color(7, 8, 28));
        panel.setLayout(new BorderLayout());
        
        // Message label with yellow text
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panel.add(messageLabel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(44, 46, 95));
        
        JButton okButton = new JButton("OK");
        okButton.setForeground(Color.YELLOW);
        okButton.setBackground(new Color(44, 46, 95));
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dispose());
        
        buttonPanel.add(okButton);
        
        // Add panels to the dialog
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        
        // Set dialog properties
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
}