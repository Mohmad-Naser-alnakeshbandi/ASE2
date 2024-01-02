package complaint.Repository;
import complaint.entity.Complaint;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {
    void addComplaint(Complaint complaint) throws IOException;
}
