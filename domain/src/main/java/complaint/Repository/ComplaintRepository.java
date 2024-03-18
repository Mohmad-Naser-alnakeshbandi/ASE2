package complaint.Repository;
import complaint.aggregate.complaints;
import complaint.entity.Complaint;

import java.io.IOException;
import java.util.List;

public interface ComplaintRepository {
    void addComplaint(Complaint complaint) throws IOException;

    List<complaints> getAllComplaint() throws IOException;
}

