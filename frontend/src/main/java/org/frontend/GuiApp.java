package org.frontend;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiApp {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Frontend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a new JButton
        JButton button = new JButton("Send HTTP request");
        frame.getContentPane().add(button);

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Send an HTTP request to the backend
                // You can use a library like Apache HttpClient or OkHttp to send HTTP requests in Java
            }
        });

        // Display the JFrame
        frame.setVisible(true);
    }
}
