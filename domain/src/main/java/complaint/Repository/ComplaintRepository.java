package complaint.Repository;
import complaint.entity.Complaint;

import java.io.IOException;
import java.util.List;

public interface ComplaintRepository {
    void addComplaint(Complaint complaint) throws IOException;

    List<Complaint> getAllComplaint() throws IOException;
}

