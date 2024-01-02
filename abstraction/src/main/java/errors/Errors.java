package errors;
import javax.swing.*;
public class Errors extends RuntimeException
{
    public Errors() {
        super();
    }
    public Errors(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message,"Error", JOptionPane.ERROR_MESSAGE);
    }


}
