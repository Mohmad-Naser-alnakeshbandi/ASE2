package persistence.complaint;
import complaint.ComplaintService;
import complaint.aggregate.complaints;
import complaint.entity.Complaint;
import complaint.Repository.ComplaintRepository;
import java.io.IOException;
import java.util.List;

public class ComplaintRepositoryBridge implements  ComplaintRepository{

    @Override
    public void addComplaint(Complaint complaint) throws IOException {
        ComplaintService complaintService = new ComplaintService();
        complaintService.addCompliantImplementation(complaint);
    }

    @Override
    public List<complaints> getAllComplaint() throws IOException {
        ComplaintService complaintService = new ComplaintService();
        return complaintService.getAllComplaintImplementation();
    }

}

