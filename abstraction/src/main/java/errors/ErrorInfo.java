package errors;

import javax.swing.*;
import java.awt.*;

public class ErrorInfo extends JFrame {
    private JLabel errorMessageLabel;

    public ErrorInfo(String errorMessage) {
        super("Error");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        errorMessageLabel = new JLabel(errorMessage);
        errorMessageLabel.setHorizontalAlignment(JLabel.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(errorMessageLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
}
