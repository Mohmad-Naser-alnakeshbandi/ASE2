package persistence.complaint;
import complaint.ComplaintService;
import complaint.entity.Complaint;
import complaint.Repository.ComplaintRepository;
import java.io.IOException;

public class ComplaintRepositoryBridge implements  ComplaintRepository{

    @Override
    public void addComplaint(Complaint complaint) throws IOException {
        ComplaintService complaintService = new ComplaintService();
        complaintService.addCompliantImplementation(complaint);
    }

}

