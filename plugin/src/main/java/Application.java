import gui.complaint.GUIComplaint;
import persistence.complaint.ComplaintRepositoryBridge;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComplaintRepositoryBridge complaintRepositoryBridge = new ComplaintRepositoryBridge();
                new GUIComplaint(complaintRepositoryBridge).init();
            }
        });


    }
}



