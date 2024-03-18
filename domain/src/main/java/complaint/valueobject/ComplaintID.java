package complaint.valueobject;

import java.util.Objects;
import java.util.Random;

public class ComplaintID {

    String complaintID;
    public String getComplaintId()
    {
        int upperBound = 1000;
        // Create an instance of Random class
        Random random = new Random();
        // Generate a random number between 100 and 999 (inclusive)
        int randomThreeDigitNumber = random.nextInt(upperBound - 100) + 100;
        return String.format("C%03d", randomThreeDigitNumber);
    }
    public ComplaintID() {}

    public ComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    @Override
    public String toString() {
        return getComplaintId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplaintID that)) return false;
        return Objects.equals(getComplaintId(), that.getComplaintId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComplaintId());
    }
}
