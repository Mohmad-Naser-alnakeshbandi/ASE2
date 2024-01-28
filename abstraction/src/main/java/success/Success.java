package success;

import javax.swing.*;

public class Success  {
    public Success(String title,String message) {
        JOptionPane.showMessageDialog(null, message,title, JOptionPane.INFORMATION_MESSAGE);
    }
}
