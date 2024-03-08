package persistence.complaint;
import complaint.ComplaintService;
import complaint.entity.Complaint;
import complaint.Repository.ComplaintRepository;
import customerreport.valueobject.CustomerID;

import java.io.IOException;
import java.util.List;

public class ComplaintRepositoryBridge implements  ComplaintRepository{

    @Override
    public void addComplaint(Complaint complaint) throws IOException {
        ComplaintService complaintService = new ComplaintService();
        complaintService.addCompliantImplementation(complaint);
    }

    /**
     * @param customerID
     * @return
     */
    @Override
    public List<Complaint> queryComplainybyCustomer(CustomerID customerID) {
        return null;
    }
}

