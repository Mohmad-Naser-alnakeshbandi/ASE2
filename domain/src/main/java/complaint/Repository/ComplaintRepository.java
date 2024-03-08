package complaint.Repository;
import complaint.entity.Complaint;
import customerreport.valueobject.CustomerID;
import errors.Errors;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {
    void addComplaint(Complaint complaint) throws Exception;

    List<Complaint> queryComplainybyCustomer(CustomerID customerID);
}

