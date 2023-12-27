package complaint.Repository;
import complaint.entity.Complaint;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository
{
    List<Complaint> getAll() throws IOException;
    public void getComplaintByID(long id) throws IOException;
}
