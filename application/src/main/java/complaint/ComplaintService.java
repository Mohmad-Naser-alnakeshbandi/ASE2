package complaint;
import complaint.entity.Complaint;
import complaint.Repository.ComplaintRepository;
import java.io.IOException;

public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    // Constructor injection
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public void addCompliantImplementation(Complaint complaint) throws IOException {
        complaintRepository.addComplaint(complaint);
    }
}
