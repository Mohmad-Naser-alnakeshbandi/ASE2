package complaint.Repository;
import complaint.entity.Complaint;

public interface ComplaintRepository {
    void addComplaint(Complaint complaint) throws Exception;
}

